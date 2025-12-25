# Selenium Java Maven TestNG Automation Project

This project is a Selenium automation framework built using Java, Maven, and TestNG. It is designed to automate 'https://www.nseindia.com/' web application testing.

## Prerequisites

Before setting up and running the project, ensure you have the following installed:

1. **Java Development Kit (JDK)** - Version 8 or later.
2. **Apache Maven** - Build automation tool.
3. **IntelliJ IDEA/Eclipse** - IDE for Java development (optional but recommended).
4. **Google Chrome** and **ChromeDriver** (ensure the version of ChromeDriver matches your installed Chrome browser version).

## Project Structure

- **src/main/java**: Contains utility classes and helper methods.
- **src/test/java**: Contains the TestNG test classes.
- **pom.xml**: Maven Project Object Model file to manage dependencies.
- **testng.xml**: TestNG configuration file for test execution.

## Setup Instructions

Follow these steps to set up the project:

### Step 1: Import the Project

- Open your preferred IDE (IntelliJ IDEA/Eclipse).
- Import the project as a Maven project.

### Step 3: Install Dependencies

Run the following command to download and install the required dependencies:

```bash
mvn clean install
```

### Step 4: Configure Browser Driver

Ensure the appropriate browser driver (e.g., ChromeDriver) is available in your system's `PATH`. Alternatively, you can specify the driver path in your code, like this:

```java
System.setProperty("webdriver.chrome.driver", "<path-to-chromedriver>");
```

### Step 5: Configure TestNG XML

The `testng.xml` file is pre-configured to specify which test cases to run. You can edit this file to update test settings or add/remove test classes.

### Step 6: Adding Test data 
In @dataprovider method which is stockData() in which user can add stocks name as available on 'nseindia' website. 
for eg. Tata Motors Pvt Ltd, GAIL (INDIA) PVT LTD
        
## Running Tests

You can run the tests using either the IDE or the command line.

### Option 1: Run via IDE

1. Open the `testng.xml` file in your IDE.
2. Right-click and select "Run" or "Run as TestNG Suite".

### Option 2: Run via Command Line

Use the following Maven command to execute the tests:

```bash
mvn test
```

## Test Reports

After execution, TestNG generates a default test report in the `test-output` folder. You can find the detailed HTML report at:

```bash
test-output/index.html
```
Also after execution Extent Report generates Extent Report in 'ExtentReports' folder.
## Adding New Test Cases

1. Create a new test class in the `src/test/java` directory.
2. Annotate your test methods with `@Test` from TestNG.
3. Add the new test class to the `testng.xml` file for execution.

## Troubleshooting

- Ensure all dependencies are resolved by running `mvn dependency:resolve`.
- Verify the browser driver path is correctly set.
- Update the `pom.xml` file if additional dependencies are required.

## Technologies Used

- **Selenium WebDriver**: For browser automation.
- **Java**: Programming language for writing test scripts.
- **Maven**: Dependency management and build tool.
- **TestNG**: For test execution and reporting.

---

Happy Testing!