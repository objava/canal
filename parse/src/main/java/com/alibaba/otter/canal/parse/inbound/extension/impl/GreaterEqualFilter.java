package com.alibaba.otter.canal.parse.inbound.extension.impl;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import java.util.function.Predicate;

public class GreaterEqualFilter extends BaseFilter {

  private String field;
  private String comparable;

  public GreaterEqualFilter(String field, String comparable) {
    this.field = field;
    this.comparable = comparable;
  }

  @Override
  public boolean test(RowData rowData) {
    for (Column column : rowData.getBeforeColumnsList()) {
      if (field.equalsIgnoreCase(column.getName().toLowerCase())) {
        return column.getValue().compareTo(comparable) >= 0;
      }
    }
    return true;
  }

  @Override public String toString() {
    return "GreaterEqualFilter{" + "field='" + field + '\'' + ", comparable='" + comparable + '\'' + '}';
  }
}
