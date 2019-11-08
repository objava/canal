package com.alibaba.otter.canal.parse.inbound.extension.impl;

import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import java.util.function.Predicate;

public class GreaterEqualFilter implements Predicate<RowData> {

  private String field;
  private Comparable comparable;

  public GreaterEqualFilter(String field, Comparable comparable) {
    this.field = field;
    this.comparable = comparable;
  }

  @Override
  public boolean test(RowData rowData) {
    return false;
  }
}
