package hudson.plugins.logparser.dsl;

import hudson.Extension;
import javaposse.jobdsl.dsl.Context;

/**
 * Created by jeremymarshall on 31/01/2015.
 */
@Extension
public final class DslLogParserPublisherContext implements Context {

    public boolean unstableOnWarning = false;
    public boolean failBuildOnError = true;
    public boolean showGraphs = false;
    public String parsingRulesPath = null;
    public boolean useProjectRule = false;
    public String projectRulePath = null;

    public void parsingRulesPath(String parsingRulesPath) {
        this.parsingRulesPath = parsingRulesPath;
        this.useProjectRule = false;
        this.projectRulePath = null;
    }

    public void projectRulePath(String projectRulePath) {
        this.projectRulePath = projectRulePath;
        this.useProjectRule = true;
        this.parsingRulesPath = null;
    }

    public void unstableOnWarning(boolean unstableOnWarning) {
        this.unstableOnWarning = unstableOnWarning;
    }

    public void failBuildOnError(boolean failBuildOnError) {
        this.failBuildOnError = failBuildOnError;
    }

    public void showGraphs(boolean showGraphs) {
        this.showGraphs = showGraphs;
    }

}
