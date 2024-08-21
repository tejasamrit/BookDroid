package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p009v1.Value;

public class NumericIncrementTransformOperation implements TransformOperation {
    private Value operand;

    private long safeIncrement(long j, long j2) {
        long j3 = j + j2;
        return ((j ^ j3) & (j2 ^ j3)) >= 0 ? j3 : j3 >= 0 ? Long.MIN_VALUE : Long.MAX_VALUE;
    }

    public Value applyToRemoteDocument(Value value, Value value2) {
        return value2;
    }

    public NumericIncrementTransformOperation(Value value) {
        Assert.hardAssert(Values.isNumber(value), "NumericIncrementTransformOperation expects a NumberValue operand", new Object[0]);
        this.operand = value;
    }

    public Value applyToLocalView(Value value, Timestamp timestamp) {
        Value computeBaseValue = computeBaseValue(value);
        if (Values.isInteger(computeBaseValue) && Values.isInteger(this.operand)) {
            return (Value) Value.newBuilder().setIntegerValue(safeIncrement(computeBaseValue.getIntegerValue(), operandAsLong())).build();
        } else if (Values.isInteger(computeBaseValue)) {
            return (Value) Value.newBuilder().setDoubleValue(((double) computeBaseValue.getIntegerValue()) + operandAsDouble()).build();
        } else {
            Assert.hardAssert(Values.isDouble(computeBaseValue), "Expected NumberValue to be of type DoubleValue, but was ", value.getClass().getCanonicalName());
            return (Value) Value.newBuilder().setDoubleValue(computeBaseValue.getDoubleValue() + operandAsDouble()).build();
        }
    }

    public Value getOperand() {
        return this.operand;
    }

    public Value computeBaseValue(Value value) {
        return Values.isNumber(value) ? value : (Value) Value.newBuilder().setIntegerValue(0).build();
    }

    private double operandAsDouble() {
        if (Values.isDouble(this.operand)) {
            return this.operand.getDoubleValue();
        }
        if (Values.isInteger(this.operand)) {
            return (double) this.operand.getIntegerValue();
        }
        throw Assert.fail("Expected 'operand' to be of Number type, but was " + this.operand.getClass().getCanonicalName(), new Object[0]);
    }

    private long operandAsLong() {
        if (Values.isDouble(this.operand)) {
            return (long) this.operand.getDoubleValue();
        }
        if (Values.isInteger(this.operand)) {
            return this.operand.getIntegerValue();
        }
        throw Assert.fail("Expected 'operand' to be of Number type, but was " + this.operand.getClass().getCanonicalName(), new Object[0]);
    }
}
