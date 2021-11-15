package stories.converters;

import com.dominic.kata.bankaccount.domain.Amount;
import org.jbehave.core.steps.ParameterConverters;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class AmountConverter implements ParameterConverters.ParameterConverter<Amount> {
    @Override
    public boolean accept(Type type) {
        if (type instanceof Class<?>) {
            return Amount.class.isAssignableFrom((Class<?>) type);
        }
        return false;
    }

    @Override
    public Amount convertValue(String s, Type type) {
        return Amount.of(new BigDecimal(s));
    }

}
