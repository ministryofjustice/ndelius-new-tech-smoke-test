import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

driver = {
    def chromeDriverPath = getClass().getResource(String.format("webdriver/%s/chromedriver", isMac() ? "mac64" : "linux64"))
    System.setProperty('webdriver.chrome.driver', chromeDriverPath.getPath())
    def options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--disable-gpu");

    def driverInstance = new ChromeDriver(options)


    driverInstance.manage().window().maximize()
    driverInstance
}

static def isMac() {
    def OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
    return ((OS.contains("mac")) || (OS.contains("darwin")));
}


baseUrl = System.getenv('DELIUS_WRAPPER_URL') ?: "http://delius-wrapper-smoke-test.eu-west-2.elasticbeanstalk.com/"
reportsDir = "build/geb-reports"
