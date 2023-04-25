import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

/*
 * 1) User login to his account
 * 2) Verify element below appear in the popup
 *    - forgot password
 *    - remember me
 *    - blured submit button
 *    - register link
 * 3) fill in username
 * 4) verify submit buttun is still blured
 * 5) fill is password
 * 6) click on submit button to login
 */
WebUI.click(findTestObject('Zi-O/MainPage/btn_login'))

WebUI.verifyElementPresent(findTestObject('Zi-O/Login/PopUp'), 2)

WebUI.verifyElementPresent(findTestObject('Zi-O/Login/link_forgotPassword'), 2)

WebUI.verifyElementPresent(findTestObject('Zi-O/Login/checkBox_checked_rememberMe'), 2)

WebUI.verifyElementPresent(findTestObject('Zi-O/Login/label_rememberMe'), 2)

WebUI.verifyElementPresent(findTestObject('Zi-O/Login/btn_disabled_submit'), 2)

WebUI.verifyElementPresent(findTestObject('Zi-O/Login/link_register'), 2)

WebUI.setText(findTestObject('Zi-O/Login/text_username'), userLogin)

WebUI.verifyElementPresent(findTestObject('Zi-O/Login/btn_disabled_submit'), 2)

WebUI.setText(findTestObject('Zi-O/Login/text_password'), password)

WebUI.click(findTestObject('Zi-O/Login/btn_submit'))

WebUI.delay(1)

