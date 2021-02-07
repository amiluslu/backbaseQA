package com.amilus.backbaseqa.cases;

import com.amilus.backbaseqa.base.Listener;
import com.amilus.backbaseqa.base.ScreenShotTaker;
import com.amilus.backbaseqa.base.TestBase;
import com.amilus.backbaseqa.pages.EditorPage;
import com.amilus.backbaseqa.pages.HomePage;
import com.amilus.backbaseqa.pages.ProfilePage;
import com.amilus.backbaseqa.pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SignInProfileArticleEditor extends TestBase {

    SignUpPage signUpPage = new SignUpPage();
    EditorPage editorPage = new EditorPage();
    HomePage homePage = new HomePage();
    ProfilePage profilePage = new ProfilePage();

    int beforeArticleNumber = 0;
    int afterArticleNumber = 0;

    @Test(priority = 0)
    public void signInWithUsername() {
        homePage.clickSignInLink();
        signUpPage.waitUntilUrlContains("/login");
        signUpPage.setUsername("amilus");
        signUpPage.setPassword("amiluslu3458");
        signUpPage.clickSignInSubmit();
        if(signUpPage.validateErrorMessage() != null){
            Listener.test.get().fail("Bug Found: Username entered but Email invalid error occured !!");
            Assert.fail("Bug Found: Username entered but Email invalid error occured !!");
        }
    }

    @Test(priority = 1)
    public void signInWithEmail() {
        homePage.clickSignInLink();
        signUpPage.setUsername("amilus@amilus.com");
        signUpPage.setPassword("amiluslu3458");
        signUpPage.clickSignInSubmit();
        String actualUserName = signUpPage.validateLogin("amilus");
        Assert.assertEquals(actualUserName,"amilus");
        Listener.test.get().pass("Expected Username: amilus Actual Username: "+actualUserName);
    }

    @Test(priority = 2)
    public void newPost() {
        profilePage.clickUsernameLabel("amilus");
        profilePage.waitUntilUrlContains("/profile");
        beforeArticleNumber = profilePage.getArticleListItemCount();
        profilePage.clickNewPost();
        editorPage.waitUntilUrlContains("/editor");
        editorPage.setArticleTitle("Article Title Amilus");
        editorPage.setSubject("Subject Amilus");
        editorPage.setContent("Content Amilus");
        editorPage.setTags("amilus");
        editorPage.clickPublishArticle();
        profilePage.clickUsernameLabel("amilus");
        profilePage.waitUntilUrlContains("/profile");
        afterArticleNumber = profilePage.getArticleListItemCount();
        Assert.assertEquals((beforeArticleNumber+1),afterArticleNumber);
        Listener.test.get().pass("Before Article Number: "+(beforeArticleNumber+1)+" After Article Number: "+afterArticleNumber);
    }

    @Test(priority = 3,dependsOnMethods = "newPost")
    public void makeComment(){
        profilePage.clickFirstArticle();
        editorPage.waitUntilUrlContains("/article");
        editorPage.setComment("Test Comment");
        editorPage.clickPostComment();
        Assert.assertEquals(1,editorPage.getNumberOfComment().size());
        Listener.test.get().pass("Actual Comment Number: 1"+" Expected Comment Number: "+editorPage.getNumberOfComment().size());
    }

    @Test(priority = 4,dependsOnMethods = "makeComment")
    public void deleteComment(){
        editorPage.clickDeleteComment();
        Assert.assertEquals(0,editorPage.getNumberOfComment().size());
        Listener.test.get().pass("Actual Comment Number: 0"+" Expected Comment Number: "+editorPage.getNumberOfComment().size());
    }

    @Test(priority = 5,dependsOnMethods = "newPost")
    public void editArticle(){
        editorPage.clickEditArticle();
        editorPage.waitUntilUrlContains("/editor");
        editorPage.setArticleTitle("Article Title Amilus Edit");
        editorPage.setSubject("Subject Amilus Edit");
        editorPage.setContent("Content Amilus Edit");
        editorPage.setTags("amilus,edit");
        editorPage.clickPublishArticle();
        editorPage.verifyArticleHeader("Article Title Amilus Edit");
    }

    @Test(priority = 6,dependsOnMethods = "newPost")
    public void deleteArticle(){
        editorPage.clickDeleteArticle();
        profilePage.clickUsernameLabel("amilus");
        profilePage.waitUntilUrlContains("/profile");
        afterArticleNumber = profilePage.getArticleListItemCount();
        Assert.assertEquals((beforeArticleNumber),afterArticleNumber);
        Listener.test.get().pass("Before Article Number: "+beforeArticleNumber+" After Article Number: "+afterArticleNumber);
    }
}
