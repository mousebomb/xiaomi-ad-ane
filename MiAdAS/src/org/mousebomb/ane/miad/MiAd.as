/**
 * Created by rhett on 16/10/1.
 */
package org.mousebomb.ane.miad
{

	import flash.events.EventDispatcher;
	import flash.external.ExtensionContext;
	import flash.system.Capabilities;

	public class MiAd extends EventDispatcher
	{
		private static const EXTENSION_ID : String = "org.mousebomb.ane.miad";

		private static var _instance : MiAd;

		private var _context : ExtensionContext;

		public function MiAd()
		{
			if (!_instance)
			{
				_context = ExtensionContext.createExtensionContext(EXTENSION_ID, null);
				if (!_context)
				{
					throw Error("ERROR - Extension context is null. Please check if extension.xml is setup correctly.");
					return;
				}

				_instance = this;
			}
			else
			{
				throw Error("This is a singleton, use getInstance(), do not call the constructor directly.");
			}
		}

		public static function getInstance() : MiAd
		{
			return _instance ? _instance : new MiAd();
		}

		public function setDebugMode( b:Boolean ):Boolean
		{
			return _context.call("setDebugMode",b) as Boolean;
		}

		public function setKeys( appID:String, splashID:String, interstitialID:String ):void
		{
			_context.call("setKeys",appID,splashID,interstitialID);
		}
		public function showInterstitial():void
		{
			_context.call("showInterstitial" );
		}

		public function cacheInterstitial():void
		{
			_context.call("cacheInterstitial" );
		}

	}
}
