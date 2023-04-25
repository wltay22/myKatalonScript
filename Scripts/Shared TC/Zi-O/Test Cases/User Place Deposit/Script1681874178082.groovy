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

CustomKeywords.'mcit.com.highlight.HighlightElement.highlight'()

WebUI.enableSmartWait()

'User access to Zi-O'
WebUI.callTestCase(findTestCase('Shared TC/Zi-O/Landing Page Check/Access to Web'), [:], FailureHandling.STOP_ON_FAILURE)

'User login in the Zi-O page'
WebUI.callTestCase(findTestCase('Shared TC/Zi-O/Landing Page Check/User Login'), [('password') : password, ('userLogin') : userLogin], 
    FailureHandling.STOP_ON_FAILURE)

'Verification the user name and amount'
WebUI.callTestCase(findTestCase('Shared TC/Zi-O/Landing Page Check/Verify User Profile'), [('userLogin') : userLogin, ('initialAmount') : initialAmount], 
    FailureHandling.STOP_ON_FAILURE)

'User make deposit in Zi-O'
WebUI.callTestCase(findTestCase('Shared TC/Zi-O/Transaction/Make Deposit/Make Deposit'), [:], FailureHandling.STOP_ON_FAILURE)

'Verification on the History page - Deposit status Pending'
WebUI.callTestCase(findTestCase('Shared TC/Zi-O/Transaction/History/Verify Pending Deposit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

