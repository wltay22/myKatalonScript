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

'Login to SY'
WebUI.callTestCase(findTestCase('Shared TC/SY/Login/Login-SY'), [('syPassword') : syPassword, ('syLogin') : syLogin], FailureHandling.STOP_ON_FAILURE)

'Navigate to Pending Deposit page'
WebUI.callTestCase(findTestCase('Shared TC/SY/Menu Navigation/Navigate - Pending Deposit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Shared TC/SY/Test Cases/Verify Approve Pending Deposit in SY'), [('syLogin') : syLogin, ('syPassword') : syPassword
        , ('userLogin') : userLogin, ('initialAmount') : initialAmount], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Shared TC/SY/Test Cases/Verify Deposit Added'), [('userLogin') : userLogin, ('initialAmount') : initialAmount], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()