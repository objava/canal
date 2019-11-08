/*
 * Copyright (C) 2010-2101 Alibaba Group Holding Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.otter.canal.parse.inbound.extension;

import com.alibaba.otter.canal.parse.inbound.extension.impl.GreaterEqualFilter;
import com.alibaba.otter.canal.parse.inbound.extension.impl.GreaterThanFilter;
import com.alibaba.otter.canal.parse.inbound.extension.impl.ValueInFilter;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.google.common.base.Splitter;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author jianghang 2012-11-7 下午02:11:46
 * @version 4.1.2
 */
public class FilterFactory {

  /**
   * @param config 配置字符串<br/> "trading_date:gt:2019-01-01"<br/> "ticker:in:REGGG001,REGGG002"<br/> "ticker:matched:REGGG\d{3}"<br/>
   * "ticker:matched:REGGG\d{3}:and:trading_date:gt:2019-01-01"<br/>
   * @return 过滤器
   */
  public static Predicate<RowData> createFilter(String config) {
    if (config.contains(":and:")) {
      final Splitter andSplitter = Splitter.on(":and:");
      Predicate<RowData> result = null;
      for (String c : andSplitter.split(config)) {
        Predicate<RowData> rowData = createFilter(c);
        if (result == null) {
          result = rowData;
        } else {
          result = result.and(rowData);
        }
      }
      return result;
    }
    final Splitter splitter = Splitter.on(":");
    final Splitter commaSplitter = Splitter.on(",");
    final List<String> strings = splitter.splitToList(config);
    if (strings.size() != 3) {
      throw new IllegalArgumentException("配置格式错误，应为 trading_date:gt:2019-01-01，实际为:" + config);
    }
    final String op = strings.get(1);
    switch (op) {
      case "gt":
        return new GreaterThanFilter(strings.get(0), strings.get(2));
      case "ge":
        return new GreaterEqualFilter(strings.get(0), strings.get(2));
      case "in":
        return new ValueInFilter(strings.get(0), commaSplitter.splitToList(strings.get(2)));
      default:
        throw new IllegalArgumentException("配置格式错误，应为 trading_date:gt:2019-01-01，实际为:" + config);
    }
  }
}
