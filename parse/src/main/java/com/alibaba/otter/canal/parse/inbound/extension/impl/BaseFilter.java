package com.alibaba.otter.canal.parse.inbound.extension.impl;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseFilter implements Predicate<RowData> {

    public Predicate<RowData> and(Predicate<? super RowData> other) {
        Objects.requireNonNull(other);
        return new Predicate<RowData>() {

            @Override public boolean test(RowData t) {
                return BaseFilter.this.test(t) && other.test(t);
            }

            @Override public String toString() {
                return BaseFilter.this.toString() + " or " + other.toString();
            }
        };
    }

    public Predicate<RowData> negate() {
        return new Predicate<RowData>() {

            @Override public boolean test(RowData t) {
                return !BaseFilter.this.test(t);
            }

            @Override public String toString() {
                return " not " + BaseFilter.this.toString();
            }
        };
    }

    public Predicate<RowData> or(Predicate<? super RowData> other) {
        Objects.requireNonNull(other);
        return new Predicate<RowData>() {

            @Override public boolean test(RowData t) {
                return BaseFilter.this.test(t) || other.test(t);
            }

            @Override public String toString() {
                return BaseFilter.this.toString() + " and " + other.toString();
            }
        };
    }

}
