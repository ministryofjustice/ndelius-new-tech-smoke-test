import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.logging.LoggingPreferences
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

driver = {
    def chromeDriverPath = getClass().getResource(String.format("webdriver/%s/chromedriver", isMac() ? "mac64" : "linux64"))
    System.setProperty('webdriver.chrome.driver', chromeDriverPath.getPath())
    def options = new ChromeOptions();
    if (!isMac()) {
        options.addArguments("--headless");
    }
    options.addArguments("window-size=1920,1080");
    options.addArguments("--allow-insecure-localhost");
    options.addArguments("--disable-gpu");
    options.addArguments("--disable-web-security")
    options.addArguments("--allow-running-insecure-content")
    def experimentalFlags = ['same-site-by-default-cookies@2','cookies-without-same-site-must-be-secure@2', 'enable-experimental-cookie-features@2']
    def chromeLocalStatePrefs = ["browser.enabled_labs_experiments" : experimentalFlags]
    options.setExperimentalOption("localState", chromeLocalStatePrefs)

    def driverInstance = new ChromeDriver(options)

    driverInstance
}

static def isMac() {
    def OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
    return ((OS.contains("mac")) || (OS.contains("darwin")));
}

baseUrl = getBaseUrl()
reportsDir = "build/geb-reports"
atCheckWaiting = true

static def getBaseUrl() {
    def url = System.getenv('DELIUS_WRAPPER_URL') ?: "http://localhost:3000/"
    println("Base url is " + url)
    return url
}