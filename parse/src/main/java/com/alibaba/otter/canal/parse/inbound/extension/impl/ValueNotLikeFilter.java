package com.alibaba.otter.canal.parse.inbound.extension.impl;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.function.Predicate;

public class ValueNotLikeFilter extends BaseFilter {

    private String       field;
    private List<String> matchedValues = Lists.newArrayList();

    public ValueNotLikeFilter(String field, List<String> matchedValues) {
        this.field = field;
        this.matchedValues.addAll(matchedValues);
    }

    @Override public boolean test(RowData rowData) {
        for (Column column : rowData.getBeforeColumnsList()) {
            if (field.equalsIgnoreCase(column.getName().toLowerCase())) {
                for (String matchedValue : matchedValues) {
                    if (matchedValue.matches(column.getValue())) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    @Override public String toString() {
        return "ValueNotLikeFilter{" + "field='" + field + '\'' + ", matchedValues=" + matchedValues + '}';
    }
}
