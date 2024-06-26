/**
 * Copyright 2020-2023 the original author or Linlan authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.linlan.datas.core.utils;

import io.linlan.datas.core.provider.result.ColumnIndex;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 
 * Filename:AggregateCollector.java
 * Desc: 聚合收集器类，用于进行聚合处理
 *
 * @author Linlan
 * CreateTime:2020/12/20 22:12
 *
 * @version 1.0
 * @since 1.0
 *
 */
public class AggregateCollector<T> implements Collector<T[], Object[], Double[]> {
    private List<ColumnIndex> valueList;
    private List<Collector> collectors;

    public static <T> AggregateCollector<T> getCollector(List<ColumnIndex> valueList) {
        return new AggregateCollector(valueList);
    }

    private AggregateCollector(List<ColumnIndex> valueList) {
        this.valueList = valueList;
        this.collectors = new ArrayList<>(valueList.size());
        valueList.stream().forEach(e -> collectors.add(null));
        IntStream.range(0, valueList.size()).forEach(i -> collectors.set(i, newCollector(valueList.get(i))));
    }

    private double toDouble(Object o) {
        if (o instanceof Double) {
            return ((Double) o).doubleValue();
        } else {
            double result = 0;
            try {
                result = Double.parseDouble((String) o);
            } catch (Exception e) {
            }
            return result;
        }
    }

    private Collector newCollector(ColumnIndex columnIndex) {
        switch (columnIndex.getAggType()) {
            case "sum":
                return Collectors.summingDouble(this::toDouble);
            case "avg":
                return Collectors.averagingDouble(this::toDouble);
            case "max":
                return Collectors.maxBy(Comparator.comparingDouble(this::toDouble));
            case "min":
                return Collectors.minBy(Comparator.comparingDouble(this::toDouble));
            case "distinct":
                return new CardinalityCollector();
            default:
                return Collectors.counting();
        }
    }

    @Override
    public Supplier<Object[]> supplier() {
        //new value row array
        return () -> {
            Object[] container = new Object[valueList.size()];
            IntStream.range(0, valueList.size()).forEach(i -> container[i] = collectors.get(i).supplier().get());
            return container;
        };
    }

    @Override
    public BiConsumer<Object[], T[]> accumulator() {
        return (array, e) ->
                IntStream.range(0, array.length).forEach(i -> {
                    collectors.get(i).accumulator().accept(array[i], e[valueList.get(i).getIndex()]);
                });

    }

    @Override
    public BinaryOperator<Object[]> combiner() {
        return (a, b) -> {
            IntStream.range(0, a.length).forEach(i -> a[i] = collectors.get(i).combiner().apply(a[i], b[i]));
            return a;
        };
    }

    @Override
    public Function<Object[], Double[]> finisher() {
        return (array) -> {
            Double[] result = new Double[array.length]; //TODO new?
            IntStream.range(0, array.length).forEach(i -> {
                Object r = collectors.get(i).finisher().apply(array[i]);
                if (r instanceof Double) {
                    result[i] = (Double) r;
                } else if (r instanceof Long) {
                    result[i] = ((Long) r).doubleValue();
                } else if (r instanceof Integer) {
                    result[i] = ((Integer) r).doubleValue();
                } else if (r instanceof Optional) {
                    result[i] = toDouble(((Optional) r).get());
                }

            });
            return result;
        }

                ;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
