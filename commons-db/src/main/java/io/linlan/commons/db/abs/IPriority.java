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
package io.linlan.commons.db.abs;

/**
 *
 * Filename:IPriority.java
 * Desc:
 *
 * CreateTime:2020-08-06 11:42 AM
 *
 * @version 1.0
 * @since 1.0
 *
 */
public interface IPriority {

    /**
     * Gets the priority，排序方法.
     *
     * @return the priority，排序方法
     */
    Number getPriority();

    /**
     * Gets the id.
     *
     * @return the id
     */
    Number getId();
}
