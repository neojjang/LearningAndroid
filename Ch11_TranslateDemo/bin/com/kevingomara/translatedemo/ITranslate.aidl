// This file is ITranslate.aidl under /src
package com.kevingomara.translatedemo;
interface ITranslate {
	String translate(in String text, in String from, in String to);
}