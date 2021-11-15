package com.dominic.kata.bankaccount.domain;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Enclosed.class)
public class AmountTest {

    @RunWith(Parameterized.class)
    public static class SignTests {

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {Amount.of(42), Amount.of(-42)},
                    {Amount.of(-42), Amount.of(42)},
                    {Amount.of(0), Amount.of(0)}
            });
        }

        private final Amount anAmount;
        private final Amount expected;

        public SignTests(Amount anAmount, Amount expected) {
            this.anAmount = anAmount;
            this.expected = expected;
        }

        @Test
        public void
        change_sign_should_work_correctly() {
            assertThat(anAmount.changeSign(), is(equalTo(expected)));
        }
    }

    @RunWith(Parameterized.class)
    public static class AbsoluteValueTests {

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {Amount.of(42), Amount.of(42)},
                    {Amount.of(-42L), Amount.of(42L)},
                    {Amount.of(-42.0), Amount.of(42)},
                    {Amount.of(0), Amount.of(0)}
            });
        }

        private final Amount anAmount;
        private final Amount expected;

        public AbsoluteValueTests(Amount anAmount, Amount expected) {
            this.anAmount = anAmount;
            this.expected = expected;
        }

        @Test
        public void
        absolute_value_should_work_correctly() {
            assertThat(anAmount.absoluteValue(), is(equalTo(expected)));
        }
    }

    public static class NonParametrizedTests {
        @Test
        public void
        should_be_equal_to_other_object_with_same_value() {
            Amount firstFortyTwo = Amount.of(42);
            Amount secondFortyTwo = Amount.of(42);

            assertEquals(firstFortyTwo, secondFortyTwo);
        }

        @Test
        public void
        should_be_different_from_other_object_with_different_value() {
            Amount fortyTwo = Amount.of(42);
            Amount negativeFortyTwo = Amount.of(-42);

            assertNotEquals(fortyTwo, negativeFortyTwo);
        }

        @Test public void equals_should_not_take_scale_into_consideration() {
            Amount fortyTwo = Amount.of(42);
            Amount anotherFortyTwo = Amount.of(new BigDecimal("42.00"));

            assertThat(fortyTwo, is(equalTo(anotherFortyTwo)));
        }

        @Test
        public void
        adding_two_amounts_should_return_the_correct_sum() {
            Amount one = Amount.of(1);
            Amount two = Amount.of(2);

            assertEquals(new BigDecimal(3), one.add(two).getValue());
        }

        @Test
        public void
        subtracting_two_amounts_should_return_the_correct_result() {
            Amount one = Amount.of(1);
            Amount two = Amount.of(2);

            assertThat(new BigDecimal(-1), is(equalTo(one.subtract(two).getValue())));
        }

        @Test
        public void
        should_return_money_representation() {
            Amount fortyTwo = Amount.of(42);

            assertThat("42.00", is(equalTo(fortyTwo.moneyFormat())));
        }
    }

}
