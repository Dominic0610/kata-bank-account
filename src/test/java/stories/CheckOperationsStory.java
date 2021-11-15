package stories;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import stories.converters.AmountConverter;
import stories.steps.CheckOperationsSteps;

import java.text.SimpleDateFormat;
import java.util.List;

public class CheckOperationsStory extends JUnitStory {

    @Override
    public Configuration configuration() {
        ParameterConverters parameterConverters = new ParameterConverters();
        parameterConverters.addConverters(
                new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),
                new AmountConverter());

        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder()
                                .withDefaultFormats()
                                .withFormats(
                                        StoryReporterBuilder.Format.CONSOLE,
                                        StoryReporterBuilder.Format.TXT,
                                        StoryReporterBuilder.Format.HTML
                                )
                ).useParameterConverters(parameterConverters);
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), new CheckOperationsSteps()).createCandidateSteps();
    }
}
