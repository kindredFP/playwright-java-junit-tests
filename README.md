# Project Description

This repository contains Playwright tests built with Java and leveraging JUnit as the test runner. The tests include various features such as properties file support, trace file support, SureFire Report Plugin support, and retrying failed attempts.

## Usage Instructions

To use this repository for your project, follow the steps below:

1. Clone the repository to your local machine.
2. **[In Progress]** More instructions will be added soon.

## Execution

To execute the tests, you can use the following Maven command:

```shell
mvn clean test
```

This command will compile and run the Playwright tests using the Maven Surefire Plugin.

## Features

This project includes the following features:

- **Properties file support**: You can use properties files to configure test parameters and settings.
- **Trace file support**: Playwright can generate trace files during test execution, providing detailed information about browser interactions.
- **SureFire Report Plugin support**: The Maven Surefire Report Plugin generates a comprehensive HTML report for your test results.
- **Retry failed attempts**: Tests that fail can be retried for a specified number of times to improve reliability.

## Planned Future Features

We are actively working on adding more features to this project. Here are some of the features we plan to include:

- **Externalizing test scenario data using parameterized tests**: Parameterized tests allow you to provide different test scenarios by externalizing the test data. This enables more comprehensive testing and better test coverage.
- **Parallel test execution**: Running tests in parallel to reduce overall test execution time.
- **Test environment configuration**: Setting up and tearing down test environments automatically to ensure consistent test conditions.
- **Screenshot and video recording**: Capturing screenshots and recording videos during test execution for debugging and analysis.

Stay tuned for updates as we continue to enhance the capabilities of this project.

## References

Please refer to the following resources for more information:

- [Playwright Documentation](https://playwright.dev)

Feel free to explore the code and customize it according to your project requirements.