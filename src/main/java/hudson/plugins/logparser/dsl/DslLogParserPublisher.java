package hudson.plugins.logparser.dsl;

import hudson.Extension;
import hudson.plugins.logparser.LogParserPublisher;
import javaposse.jobdsl.dsl.DslExtensionMethod;
import javaposse.jobdsl.dsl.helpers.publisher.PublisherContext;
import javaposse.jobdsl.plugin.ContextExtensionPoint;

/**
 * Created by jeremymarshall on 26/02/2015.
 */

@Extension(optional = true)
public class DslLogParserPublisher extends ContextExtensionPoint {

    @DslExtensionMethod(context = PublisherContext.class)
    public Object logParserWorkspace( String projectRulePath) {
        return new LogParserPublisher(false, false, false, null, true, projectRulePath);
    }

    @DslExtensionMethod(context = PublisherContext.class)
    public Object logParserWorkspace(String projectRulePath,
                                     boolean unstableOnWarning,
                                     boolean failBuildOnError,
                                     boolean showGraphs) {
        return new LogParserPublisher(unstableOnWarning, failBuildOnError, showGraphs, null, true, projectRulePath);
    }

    @DslExtensionMethod(context = PublisherContext.class)
    public Object logParserGlobal(String parsingRulesPath) {
        LogParserPublisher ret =  new LogParserPublisher(false, false, false, parsingRulesPath, false, null);
        return ret;
    }

    @DslExtensionMethod(context = PublisherContext.class)
    public Object logParserGlobal(String projectRulePath,
                                  boolean unstableOnWarning,
                                  boolean failBuildOnError,
                                  boolean showGraphs) {
        LogParserPublisher ret =  new LogParserPublisher(unstableOnWarning, failBuildOnError, showGraphs, projectRulePath, false, null);
        return ret;
    }

    @DslExtensionMethod(context = PublisherContext.class)
    public Object logParser(Runnable closure) {
        DslLogParserPublisherContext context = new DslLogParserPublisherContext();
        executeInContext(closure, context);

        return new LogParserPublisher(context.unstableOnWarning,
                context.failBuildOnError,
                context.showGraphs,
                context.parsingRulesPath,
                context.useProjectRule,
                context.projectRulePath);
    }
}
