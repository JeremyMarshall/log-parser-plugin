package hudson.plugins.logparser.dsl;
import hudson.Extension;
import hudson.model.Describable;
import hudson.plugins.logparser.LogParserPublisher;
import org.jenkinsci.plugins.jobdsl.stub.DslClosureUnsupported;
import org.jenkinsci.plugins.jobdsl.stub.DslNoClosureClass;
import org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Method;
import org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Parameter;
import org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Publisher;

import java.util.Collections;

/**
 * Created by jeremymarshall on 26/02/2015.
 */

@Extension
public class DslLogParserPublisher extends Publisher {
    @Override
    public String getName(){
        return "LogParserPublisher";
    }

    @Override
    public String getDescription(){
        return "Add a Log Parser";
    }

    @Override
    public final boolean hasMethods(){
        return true;
    };

    //unstableOnWarning,
    //failBuildOnError, showGraphs,
    //parsingRulesPath, useProjectRule,
    //projectRulePath

    @Method(description="Add a log parser using a file in the workspace")
    public Object logParserWorkspace(@Parameter(description="Parsing rules file") String projectRulePath) {
        return new LogParserPublisher(false, false, false, null, true, projectRulePath);
    }

    @Method(description="Add a log parser using a file in the workspace")
    public Object logParserWorkspace(@Parameter(description="Parsing rules file") String projectRulePath,
                                     @Parameter(description="Is unstable on warning") boolean unstableOnWarning,
                                     @Parameter(description="Fail on build error") boolean failBuildOnError,
                                     @Parameter(description="Show graphs") boolean showGraphs) {
        return new LogParserPublisher(unstableOnWarning, failBuildOnError, showGraphs, null, true, projectRulePath);
    }

    @Method(description="Add a log parser using a project rules file")
    public Object logParserGlobal(@Parameter(description="Project rules file") String parsingRulesPath) {
        LogParserPublisher ret =  new LogParserPublisher(false, false, false, parsingRulesPath, false, null);
        return ret;
    }

    @Method(description="Add a log parser using a project rules file")
    public Object logParserGlobal(@Parameter(description="Project rules file") String projectRulePath,
                                    @Parameter(description="Is unstable on warning") boolean unstableOnWarning,
                                    @Parameter(description="Fail on build error") boolean failBuildOnError,
                                    @Parameter(description="Show graphs") boolean showGraphs) {
        LogParserPublisher ret =  new LogParserPublisher(unstableOnWarning, failBuildOnError, showGraphs, projectRulePath, false, null);
        return ret;
    }

    @Method(description="Add a log parser with a closure", closureClass = DslLogParserPublisherClosure.class)
    public Object logParser(@Parameter(description="The closure") Object closure)
            throws DslClosureUnsupported, DslNoClosureClass, IllegalAccessException, InstantiationException
    {
        DslLogParserPublisherClosure i = (DslLogParserPublisherClosure) runClosure(closure, DslLogParserPublisherClosure.class);
        LogParserPublisher ret = new LogParserPublisher(i.unstableOnWarning, i.failBuildOnError, i.showGraphs, i.parsingRulesPath, i.useProjectRule, i.projectRulePath);
        return ret;
    }
}
