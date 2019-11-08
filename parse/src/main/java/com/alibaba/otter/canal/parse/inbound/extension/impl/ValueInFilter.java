package com.alibaba.otter.canal.parse.inbound.extension.impl;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.function.Predicate;

public class ValueInFilter implements Predicate<RowData> {

  private String field;
  private List<String> matchedValues = Lists.newArrayList();

  public ValueInFilter(String field, List<String> matchedValues) {
    this.field = field;
    this.matchedValues.addAll(matchedValues);
  }

  @Override
  public boolean test(RowData rowData) {
    for (Column column : rowData.getBeforeColumnsList()) {
      if (field.equalsIgnoreCase(column.getName().toLowerCase())) {
        return matchedValues.contains(column.getValue());
      }
    }
    return true;
  }
}
