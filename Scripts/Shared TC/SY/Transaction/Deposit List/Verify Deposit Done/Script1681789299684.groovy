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
 * 1) verify on below:
 *    - Topup Amount
 *    - Deposit Type = Bank Deposit
 *    - Deposit Info:
 *      - Bank Name
 *      - Account No
 *      - Bank Address
 *    - Status = Verified
 */

amount = WebUI.getText(findTestObject('SY/Transaction/Deposit List/column_topupAmount'))

type = WebUI.getText(findTestObject('SY/Transaction/Deposit List/column_depositType'))

info = WebUI.getText(findTestObject('SY/Transaction/Deposit List/column_depositInfo'))

remark = WebUI.getText(findTestObject('SY/Transaction/Deposit List/column_remark'))

status = WebUI.getText(findTestObject('SY/Transaction/Deposit List/column_status'))

WebUI.delay(1)

println("amount :: " + amount)

println("type :: " + type)

println("info :: " + info)

println("remark :: " + remark)

println("status :: " + status)

WebUI.delay(1)

double dAmount = new Double(amount.toString());

int amount = (int)dAmount;

stamount = amount.toString()

WebUI.verifyMatch(stamount, GlobalVariable.reloadAmount, false)

WebUI.verifyMatch(type, "Bank Deposit", false)

int length = remark.length()

WebUI.verifyNotEqual(length, 0)

WebUI.verifyMatch(status, "Verified", false)

if(info.contains(GlobalVariable.BankName) && info.contains(GlobalVariable.BankAddress) && info.contains(GlobalVariable.BankAccount)) {
	WebUI.delay(1)
}
