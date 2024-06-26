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
package io.linlan.commons.core.abs;


import java.util.List;
import java.util.Map;

/**
 * Extension of the {@code Map} interface that stores multiple values.
 * Filename:IMultiValueMap.java
 * Desc:Extension of the {@code Map} interface that stores multiple values.
 *
 * @author Arjen Poutsma
 * Createtime 2020/7/12 14:30
 *
 * @version 1.0
 * @since 1.0
 *
 */
public interface IMultiValueMap<K, V> extends Map<K, List<V>> {

    /**
     * Return the first value for the given key.
     * @param key the key
     * @return the first value for the specified key, or {@code null}
     */
    V getFirst(K key);

    /**
     * Add the given single value to the current list of values for the given key.
     * @param key the key
     * @param value the value to be added
     */
    void add(K key, V value);

    /**
     * Set the given single value under the given key.
     * @param key the key
     * @param value the value to set
     */
    void set(K key, V value);

    /**
     * Set the given values under.
     * @param values the values.
     */
    void setAll(Map<K, V> values);

    /**
     * Returns the first values contained in this {@code IMultiValueMap}.
     * @return a single value representation of this map
     */
    Map<K, V> toSingleValueMap();

}
