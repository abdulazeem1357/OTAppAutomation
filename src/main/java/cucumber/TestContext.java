package cucumber;

import fundamentals.InfrastructureEnv;
import managers.AndroidDriverManager;
import managers.PageObjectManager;

public class TestContext {
    private AndroidDriverManager androidDriverManager;
    private PageObjectManager pageObjectManager;
    private InfrastructureEnv env = new InfrastructureEnv();

    public TestContext() {
        androidDriverManager = new AndroidDriverManager(env);
        pageObjectManager = new PageObjectManager(androidDriverManager.getDriver());
    }

    public AndroidDriverManager getAndroidDriverManager() {
        return androidDriverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

}
