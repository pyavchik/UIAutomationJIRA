package testdata;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name="correctCredentials")
    public Object[][] getCorrectCredentials() {
        return new Object[][] {
                {"webinar5", "webinar5"}
        };
    }

    @DataProvider(name="correctLoginWrongPasswordCredentials")
    public Object[][] getCorrectLoginWrongPasswordCredentials() {
        return new Object[][] {
                {"webinar5", "wrongPassword3"}
        };
    }

    @DataProvider(name="wrongLoginCorrectPasswordCredentials")
    public Object[][] getWrongLoginCorrectPasswordCredentials() {
        return new Object[][] {
                {"wrongLogin3", "webinar5"}
        };
    }

    @DataProvider(name="wrongLoginWrongPasswordCredentials")
    public Object[][] getWrongLoginWrongPasswordCredentials() {
        return new Object[][] {
                {"wrongLogin3", "wrongLogin3"}
        };
    }
}
