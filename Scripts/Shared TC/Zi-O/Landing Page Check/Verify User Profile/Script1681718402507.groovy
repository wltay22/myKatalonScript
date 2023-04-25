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
 * verify on below:
 * - user name
 * - amount is match with initial amount
 */

username = WebUI.getText(findTestObject('Zi-O/MainPage/label_username'), FailureHandling.STOP_ON_FAILURE)

println("login :: " + username)

WebUI.verifyMatch(username, userLogin, false)

WebUI.delay(1)

dAmount = WebUI.getText(findTestObject('Object Repository/Zi-O/MainPage/label_amount'))

dAmount = dAmount.replace(",", "")

println("dAmount :: " + dAmount)

double dAmount = new Double(dAmount.toString());

int amount = (int)dAmount;

stamount = amount.toString()

println("amount :: " + stamount)

println("initialAmount :: " + initialAmount)

WebUI.verifyMatch(stamount, initialAmount, false)

WebUI.delay(1)