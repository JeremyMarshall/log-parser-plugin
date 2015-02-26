package hudson.plugins.logparser.dsl;

import hudson.Extension;
import org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Method;
import org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Parameter;

/**
 * Created by jeremymarshall on 31/01/2015.
 */
@Extension
public final class DslLogParserPublisherClosure extends org.jenkinsci.plugins.jobdsl.stub.annotations.dsl.Closure{

    public boolean unstableOnWarning = false;
    public boolean failBuildOnError = true;
    public boolean showGraphs = false;
    public String parsingRulesPath = null;
    public boolean useProjectRule = false;
    public String projectRulePath = null;

    @Override
    public String getName(){
        return "LogParserPublisher closure";
    }

    @Override
    public String getDescription(){
        return "LogParserPublisher closure";
    }

    @Override
    public final boolean hasMethods(){
        return true;
    };

    @Method(description="Set parsingRulesPath")
    public void parsingRulesPath(@Parameter(description="The path to the parsing rules") String parsingRulesPath) {
        this.parsingRulesPath = parsingRulesPath;
        this.useProjectRule = false;
        this.projectRulePath = null;
    }

    @Method(description="Set projectRulePath")
    public void projectRulePath(@Parameter(description="The path to the project parsing rules") String projectRulePath) {
        this.projectRulePath = projectRulePath;
        this.useProjectRule = true;
        this.parsingRulesPath = null;
    }

    @Method(description="Unstable on warning")
    public void unstableOnWarning(@Parameter(description="Is unstable on warning") boolean unstableOnWarning) {
        this.unstableOnWarning = unstableOnWarning;
    }

    @Method(description="Fail Build On Error")
    public void failBuildOnError(@Parameter(description="Fail on error") boolean failBuildOnError) {
        this.failBuildOnError = failBuildOnError;
    }

    @Method(description="Show Graphs")
    public void showGraphs(@Parameter(description="Show graphs") boolean showGraphs) {
        this.showGraphs = showGraphs;
    }

}
