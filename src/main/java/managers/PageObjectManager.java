package managers;

import fundamentals.AndroidBasics;
import helpers.HttpClientHelper;
import io.appium.java_client.android.AndroidDriver;
import pageObjects.*;

public class PageObjectManager {
    private AndroidDriver driver;
    private MainPage mainPage;
    private DownloadDataPage downloadDataPage;
    private HardwareSettingsPage hardwareSettingsPage;
    private TerminalSettingsPage terminalSettingsPage;
    private LoginToTerminalPage loginToTerminalPage;
    private RoutePage routePage;
    private SettingsPage settingsPage;
    private ShiftPage shiftPage;
    private SideMenuComponent sideMenuComponent;
    private TripPage tripPage;
    private AndroidBasics androidBasics;
    private DayPrepPage dayPrepPage;
    private ConstantsPage constantsPage;
    private CodeRevampPage codeRevampPage;
    private HttpClientHelper httpClientHelper;

    public PageObjectManager(AndroidDriver driver) {
        this.driver = driver;
    }

    public MainPage getMainPage() {
        return (mainPage == null) ? mainPage = new MainPage(driver) : mainPage;
    }

    public DownloadDataPage getDownloadDataPage() {
        return (downloadDataPage == null) ? downloadDataPage = new DownloadDataPage(driver) : downloadDataPage;
    }

    public HardwareSettingsPage getHardwareSettingsPage() {
        return (hardwareSettingsPage == null) ? hardwareSettingsPage = new HardwareSettingsPage(driver) : hardwareSettingsPage;
    }

    public TerminalSettingsPage getTerminalSettingsPage() {
        return (terminalSettingsPage == null) ? terminalSettingsPage = new TerminalSettingsPage(driver) : terminalSettingsPage;
    }

    public LoginToTerminalPage getLoginToTerminalPage() {
        return (loginToTerminalPage == null) ? loginToTerminalPage = new LoginToTerminalPage(driver) : loginToTerminalPage;
    }

    public RoutePage getRoutePage() {
        return (routePage == null) ? routePage = new RoutePage(driver) : routePage;
    }

    public SettingsPage getSettingsPage() {
        return (settingsPage == null) ? settingsPage = new SettingsPage(driver) : settingsPage;
    }

    public ShiftPage getShiftPage() {
        return (shiftPage == null) ? shiftPage = new ShiftPage(driver) : shiftPage;
    }

    public SideMenuComponent getSideMenuComponent() {
        return (sideMenuComponent == null) ? sideMenuComponent = new SideMenuComponent(driver) : sideMenuComponent;
    }

    public TripPage getTripPage() {
        return (tripPage == null) ? tripPage = new TripPage(driver) : tripPage;
    }

    public AndroidBasics getAndroidBasics() {
        return (androidBasics == null) ? androidBasics = new AndroidBasics(driver) : androidBasics;
    }

    public DayPrepPage getDayPrepPage() {
        return (dayPrepPage == null) ? dayPrepPage = new DayPrepPage(driver) : dayPrepPage;
    }

    public ConstantsPage getConstantsPage() {
        return (constantsPage == null) ? constantsPage = new ConstantsPage(driver) : constantsPage;
    }

    public CodeRevampPage getCodeRevampPage() {
        return (codeRevampPage == null) ? codeRevampPage = new CodeRevampPage(driver) : codeRevampPage;
    }
}
