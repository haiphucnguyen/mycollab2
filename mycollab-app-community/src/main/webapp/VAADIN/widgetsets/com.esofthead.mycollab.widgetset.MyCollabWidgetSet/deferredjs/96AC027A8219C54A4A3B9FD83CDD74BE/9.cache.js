$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function WBb(a){return a.g}\nfunction YBb(a,b){cBb(a,b);--a.i}\nfunction Obd(){Tb.call(this)}\nfunction eId(){Jc.call(this);this.G=xOe}\nfunction Dw(a){return (Vt(),Ut).Ne(a,'col')}\nfunction $mc(a,b){t_b(a.a,new Evc(new Uvc(Pib),'openPopup'),JK(FK(mmb,1),mne,1,3,[(GVd(),b?FVd:EVd)]))}\nfunction XBb(a,b){if(b<0){throw new AVd('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new AVd(zse+b+Ase+a.i)}}\nfunction $Bb(a,b){if(a.i==b){return}if(b<0){throw new AVd('Cannot set number of rows to '+b)}if(a.i<b){aCb((gvb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){YBb(a,a.i-1)}}}\nfunction _Bb(a,b){MAb();jBb.call(this);dBb(this,new FBb(this));gBb(this,new PCb(this));eBb(this,new DCb(this));ZBb(this,b);$Bb(this,a)}\nfunction CCb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){Ks(a.a,Dw($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){Ts(a.a,a.a.lastChild)}}}\nfunction aCb(a,b,c){var d=$doc.createElement('td');d.innerHTML=lve;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction Nbd(a){if((!a.T&&(a.T=bb(a)),QK(QK(a.T,6),211)).c&&((!a.T&&(a.T=bb(a)),QK(QK(a.T,6),211)).v==null||cYd('',(!a.T&&(a.T=bb(a)),QK(QK(a.T,6),211)).v))){return (!a.T&&(a.T=bb(a)),QK(QK(a.T,6),211)).a}return (!a.T&&(a.T=bb(a)),QK(QK(a.T,6),211)).v}\nfunction ZBb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new AVd('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){OAb(a,c,d);e=QAb(a,c,d,false);f=LCb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){$Ab(a,c,d)}}}a.g=b;CCb(a.I,b,false)}\nvar INe={48:1,7:1,14:1,27:1,32:1,30:1,33:1,31:1,3:1},JNe='com.vaadin.client.ui.colorpicker',KNe='background',uOe='showDefaultCaption',vOe='setColor',wOe='setOpen',xOe='v-colorpicker',FOe='v-default-caption-width';vrb(1,null,{});_.gC=function U(){return this.cZ};vrb(117,9,Nne);_.me=function Ej(a){return Bd(this,a,(UD(),UD(),TD))};vrb(778,26,Bse);_.me=function kBb(a){return Bd(this,a,(UD(),UD(),TD))};vrb(246,45,Dse);_.me=function SBb(a){return Bd(this,a,(UD(),UD(),TD))};vrb(622,778,Bse,_Bb);_.Jg=function bCb(){var a;a=(gvb(),dx($doc));Dt(a,lve);return a};_.Kg=function cCb(a){return WBb(this)};_.Lg=function dCb(){return this.i};_.Mg=function eCb(a,b){XBb(this,a);if(b<0){throw new AVd('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new AVd(xse+b+yse+this.g)}};_.Ng=function fCb(a){XBb(this,a)};_.g=0;_.i=0;var MS=sWd(Ume,'Grid',622);vrb(101,569,Ese);_.me=function lCb(a){return Bd(this,a,(UD(),UD(),TD))};vrb(363,9,Pse);_.me=function lDb(a){return Cd(this,a,(UD(),UD(),TD))};vrb(772,30,INe);_.wc=function Pbd(){return false};_.zc=function Qbd(){return !this.T&&(this.T=bb(this)),QK(QK(this.T,6),211)};_.lc=function Rbd(){return !this.T&&(this.T=bb(this)),QK(QK(this.T,6),211)};_.nc=function Sbd(){UK(this.Bc(),49)&&QK(this.Bc(),49).me(this)};_.pc=function Tbd(a){Mb(this,a);if(a.Mi(ave)){this.yl();(!this.T&&(this.T=bb(this)),QK(QK(this.T,6),211)).c&&((!this.T&&(this.T=bb(this)),QK(QK(this.T,6),211)).v==null||cYd('',(!this.T&&(this.T=bb(this)),QK(QK(this.T,6),211)).v))&&this.zl((!this.T&&(this.T=bb(this)),QK(QK(this.T,6),211)).a)}if(a.Mi(nue)||a.Mi($Ce)||a.Mi(uOe)){this.zl(Nbd(this));(!this.T&&(this.T=bb(this)),QK(QK(this.T,6),211)).c&&((!this.T&&(this.T=bb(this)),QK(QK(this.T,6),211)).v==null||!(!this.T&&(this.T=bb(this)),QK(QK(this.T,6),211)).v.length)&&!(!this.T&&(this.T=bb(this)),QK(QK(this.T,6),211)).J.length?Oc(this.Bc(),FOe):Wc(this.Bc(),FOe)}};var ycb=sWd(JNe,'AbstractColorPickerConnector',772);vrb(211,6,{6:1,39:1,211:1,3:1},eId);_.a=null;_.b=false;_.c=false;var Qib=sWd(zKe,'ColorPickerState',211);dme(Rq)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
