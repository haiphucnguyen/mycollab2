$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function lDb(a){return a.g}\nfunction nDb(a,b){tCb(a,b);--a.i}\nfunction Tdd(){Tb.call(this)}\nfunction jKd(){Jc.call(this);this.G=jVe}\nfunction Dw(a){return (Vt(),Ut).Ne(a,'col')}\nfunction mnc(a,b){L0b(a.a,new Exc(new Uxc(jjb),'openPopup'),NK(JK(Imb,1),ste,1,3,[(LXd(),b?KXd:JXd)]))}\nfunction mDb(a,b){if(b<0){throw new FXd('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new FXd(Hye+b+Iye+a.i)}}\nfunction pDb(a,b){if(a.i==b){return}if(b<0){throw new FXd('Cannot set number of rows to '+b)}if(a.i<b){rDb((xwb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){nDb(a,a.i-1)}}}\nfunction qDb(a,b){bCb();ACb.call(this);uCb(this,new WCb(this));xCb(this,new eEb(this));vCb(this,new UDb(this));oDb(this,b);pDb(this,a)}\nfunction TDb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){Ks(a.a,Dw($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){Ts(a.a,a.a.lastChild)}}}\nfunction rDb(a,b,c){var d=$doc.createElement('td');d.innerHTML=vBe;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction Sdd(a){if((!a.U&&(a.U=bb(a)),UK(UK(a.U,6),214)).c&&((!a.U&&(a.U=bb(a)),UK(UK(a.U,6),214)).v==null||h$d('',(!a.U&&(a.U=bb(a)),UK(UK(a.U,6),214)).v))){return (!a.U&&(a.U=bb(a)),UK(UK(a.U,6),214)).a}return (!a.U&&(a.U=bb(a)),UK(UK(a.U,6),214)).v}\nfunction oDb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new FXd('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){dCb(a,c,d);e=fCb(a,c,d,false);f=aEb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){pCb(a,c,d)}}}a.g=b;TDb(a.I,b,false)}\nvar uUe={48:1,7:1,13:1,28:1,32:1,31:1,33:1,30:1,3:1},vUe='com.vaadin.client.ui.colorpicker',wUe='background',gVe='showDefaultCaption',hVe='setColor',iVe='setOpen',jVe='v-colorpicker',rVe='v-default-caption-width';Asb(1,null,{});_.gC=function U(){return this.cZ};Asb(119,9,Ste);_.me=function Ej(a){return Bd(this,a,(YD(),YD(),XD))};Asb(795,25,Jye);_.me=function BCb(a){return Bd(this,a,(YD(),YD(),XD))};Asb(249,45,Lye);_.me=function hDb(a){return Bd(this,a,(YD(),YD(),XD))};Asb(635,795,Jye,qDb);_.Jg=function sDb(){var a;a=(xwb(),dx($doc));Dt(a,vBe);return a};_.Kg=function tDb(a){return lDb(this)};_.Lg=function uDb(){return this.i};_.Mg=function vDb(a,b){mDb(this,a);if(b<0){throw new FXd('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new FXd(Fye+b+Gye+this.g)}};_.Ng=function wDb(a){mDb(this,a)};_.g=0;_.i=0;var RS=xYd($se,'Grid',635);Asb(101,581,Mye);_.me=function CDb(a){return Bd(this,a,(YD(),YD(),XD))};Asb(369,9,Xye);_.me=function DEb(a){return Cd(this,a,(YD(),YD(),XD))};Asb(788,31,uUe);_.wc=function Udd(){return false};_.zc=function Vdd(){return !this.U&&(this.U=bb(this)),UK(UK(this.U,6),214)};_.lc=function Wdd(){return !this.U&&(this.U=bb(this)),UK(UK(this.U,6),214)};_.nc=function Xdd(){YK(this.Bc(),49)&&UK(this.Bc(),49).me(this)};_.pc=function Ydd(a){Mb(this,a);if(a.Mi(kBe)){this.Cl();(!this.U&&(this.U=bb(this)),UK(UK(this.U,6),214)).c&&((!this.U&&(this.U=bb(this)),UK(UK(this.U,6),214)).v==null||h$d('',(!this.U&&(this.U=bb(this)),UK(UK(this.U,6),214)).v))&&this.Dl((!this.U&&(this.U=bb(this)),UK(UK(this.U,6),214)).a)}if(a.Mi(xAe)||a.Mi(XIe)||a.Mi(gVe)){this.Dl(Sdd(this));(!this.U&&(this.U=bb(this)),UK(UK(this.U,6),214)).c&&((!this.U&&(this.U=bb(this)),UK(UK(this.U,6),214)).v==null||!(!this.U&&(this.U=bb(this)),UK(UK(this.U,6),214)).v.length)&&!(!this.U&&(this.U=bb(this)),UK(UK(this.U,6),214)).J.length?Oc(this.Bc(),rVe):Wc(this.Bc(),rVe)}};var Ucb=xYd(vUe,'AbstractColorPickerConnector',788);Asb(214,6,{6:1,39:1,214:1,3:1},jKd);_.a=null;_.b=false;_.c=false;var kjb=xYd(SQe,'ColorPickerState',214);jse(Rq)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
