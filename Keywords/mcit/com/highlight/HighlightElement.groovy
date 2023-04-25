package mcit.com.highlight

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

/*
 * This class implements 2 Keyword methods for Katalon Studio.
 *
 * <code>HighlightElement.on(TestObject to)</code> puts highlight on the specified HTML element.
 * <code>HighlightElement.highlight()</code> and <code>HighlightElement.highlight(List<String> keywordsToAdd)</code> modifies some built-in WebUI Keywords dynamically so that they implicity invoke <code>.on(TestObject)</code> before
 executing their own built-in processing (such as clicking the element).
 */
public class HighlightElement {

	@Keyword
	public static void on(TestObject testObject) {
		drawOutline(testObject)
	}

	private static void drawOutline(TestObject testObject) {
		try {
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> elements = WebUiCommonHelper.findWebElements(testObject, 20);
			for (WebElement element : elements) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript(
						"arguments[0].setAttribute('style','border: 3px solid red;');",
						element);
			}
		} catch (Exception e) {
			// TODO use Katalon Logging
			e.printStackTrace()
		}
	}

	//Outline: dashed red;
	/*
	 * @return list of the built-in WebUI.* keywords that have "TestObject" as the 1st argument.
	 */
	public static Set<String> getHighlightableBuiltinKeywords() {
		List<MetaMethod> metaMethods = WebUiBuiltInKeywords.metaClass.getMethods()
		//println "metaMethods.size() is ${metaMethods.size()}"
		Set<String> highlightables = new HashSet<String>()
		for (MetaMethod method in metaMethods) {
			if (method.isStatic() && method.isPublic()) {
				Class<?>[] parameterTypes = method.nativeParameterTypes
				// select a Keyword if it requires a TestObject as the 1st argument
				if ( parameterTypes.size() > 0 && parameterTypes[0].is(TestObject.class)) {
					//println "method: ${method.getName()}(${parameterTypes[0].getName()})"
					highlightables.add(method.getName())
				}
			}
		}
		return highlightables
	}


	/*
	 * these keywords are "highlighting" as default
	 */
	public static final Set<String> DEFAULT_HIGHLIGHTING_KW = new HashSet(['click', 'verifyElementPresent', 'getText', 'getAttribute', 'setText'])

	// instance variable
	private final Set<String> highlightingKW

	/* constructor
	 */
	HighlightElement() {
		this.highlightingKW = new HashSet(DEFAULT_HIGHLIGHTING_KW)
	}

	/* change some of methods of WebUiBuiltInKeywords so that they call HighlightElement.on(testObject)
	 * before invoking their original method body.
	 *
	 * This method is implemented using Groovy Metaprogramming technique. See
	 * http://docs.groovy-lang.org/latest/html/documentation/core-metaprogramming.html#metaprogramming
	 *
	 * @param keywordsToAdd you can specify additional keywords to turn "highlighting"
	 */
	@Keyword
	public void highlight(List<String> keywordsToAdd = []) {
		this.markKeywords(keywordsToAdd)
		Set<String> influencedKeywords = this.getHighlightingKeywords()
		WebUiBuiltInKeywords.metaClass.'static'.invokeMethod = { String name, args ->
			if (name in influencedKeywords) {
				TestObject to = (TestObject)args[0]
				HighlightElement.on(to)
			}
			return delegate.metaClass.getMetaMethod(name, args).invoke(delegate, args)
		}
	}


	/*
	 * @param keywordsToAdd
	 */
	public void markKeywords(List<String> keywordsToAdd = []) {
		Objects.requireNonNull(keywordsToAdd)
		Set<String> highlightables = getHighlightableBuiltinKeywords()
		keywordsToAdd.each { kw ->
			// if the specified keyword is highlight-able, then accept it
			if (highlightables.contains(kw)) {
				//println "specified keyword \"${kw}\" is marked as highlight-able"
				this.highlightingKW.add(kw)
			} else {
				println "specified keyword \"${kw}\" is not highlight-able; just ignored"
			}
		}
	}

	/**
	 *
	 * @return
	 */
	public Set<String> getHighlightingKeywords() {
		return highlightingKW.clone()
	}
}