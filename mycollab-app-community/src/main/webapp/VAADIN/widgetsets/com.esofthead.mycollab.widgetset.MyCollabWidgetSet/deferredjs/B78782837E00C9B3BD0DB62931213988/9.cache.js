$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function QCb(a){return a.g}\nfunction SCb(a,b){YBb(a,b);--a.i}\nfunction tfd(){Xb.call(this)}\nfunction _Pd(){Nc.call(this);this.G=V2e}\nfunction Uu(a){return (ks(),js).qe(a,'col')}\nfunction Toc(a,b){F0b(a.a,new iyc(new yyc(qib),'openPopup'),eJ(aJ(Qlb,1),MCe,1,3,[(J1d(),b?I1d:H1d)]))}\nfunction RCb(a,b){if(b<0){throw new D1d('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new D1d(KHe+b+LHe+a.i)}}\nfunction UCb(a,b){if(a.i==b){return}if(b<0){throw new D1d('Cannot set number of rows to '+b)}if(a.i<b){WCb((Nvb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){SCb(a,a.i-1)}}}\nfunction VCb(a,b){GBb();dCb.call(this);ZBb(this,new zCb(this));aCb(this,new JDb(this));$Bb(this,new xDb(this));TCb(this,b);UCb(this,a)}\nfunction wDb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){$q(a.a,Uu($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){hr(a.a,a.a.lastChild)}}}\nfunction WCb(a,b,c){var d=$doc.createElement('td');d.innerHTML=GKe;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction sfd(a){if((!a.U&&(a.U=eb(a)),lJ(lJ(a.U,6),221)).c&&((!a.U&&(a.U=eb(a)),lJ(lJ(a.U,6),221)).v==null||k4d('',(!a.U&&(a.U=eb(a)),lJ(lJ(a.U,6),221)).v))){return (!a.U&&(a.U=eb(a)),lJ(lJ(a.U,6),221)).a}return (!a.U&&(a.U=eb(a)),lJ(lJ(a.U,6),221)).v}\nfunction TCb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new D1d('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){IBb(a,c,d);e=KBb(a,c,d,false);f=FDb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){UBb(a,c,d)}}}a.g=b;wDb(a.I,b,false)}\nvar P2e='showDefaultCaption',Q2e='setColor',R2e='setOpen',S2e='background',T2e={45:1,7:1,11:1,27:1,31:1,33:1,32:1,30:1,3:1},U2e='com.vaadin.client.ui.colorpicker',V2e='v-colorpicker',k3e='v-default-caption-width';asb(1,null,{});_.gC=function X(){return this.cZ};asb(125,9,AHe);_.jf=function yzb(a){return Fd(this,a,(nC(),nC(),mC))};asb(811,26,MHe);_.jf=function eCb(a){return Fd(this,a,(nC(),nC(),mC))};asb(257,46,OHe);_.jf=function MCb(a){return Fd(this,a,(nC(),nC(),mC))};asb(643,811,MHe,VCb);_.vg=function XCb(){var a;a=(Nvb(),uv($doc));Ur(a,GKe);return a};_.wg=function YCb(a){return QCb(this)};_.xg=function ZCb(){return this.i};_.yg=function $Cb(a,b){RCb(this,a);if(b<0){throw new D1d('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new D1d(IHe+b+JHe+this.g)}};_.zg=function _Cb(a){RCb(this,a)};_.g=0;_.i=0;var eR=w2d(sCe,'Grid',643);asb(107,590,PHe);_.jf=function fDb(a){return Fd(this,a,(nC(),nC(),mC))};asb(382,9,$He);_.jf=function fEb(a){return Gd(this,a,(nC(),nC(),mC))};asb(801,33,T2e);_.wc=function ufd(){return false};_.zc=function vfd(){return !this.U&&(this.U=eb(this)),lJ(lJ(this.U,6),221)};_.lc=function wfd(){return !this.U&&(this.U=eb(this)),lJ(lJ(this.U,6),221)};_.nc=function xfd(){pJ(this.Bc(),50)&&lJ(this.Bc(),50).jf(this)};_.pc=function yfd(a){Qb(this,a);if(a.Di(uKe)){this.Il();(!this.U&&(this.U=eb(this)),lJ(lJ(this.U,6),221)).c&&((!this.U&&(this.U=eb(this)),lJ(lJ(this.U,6),221)).v==null||k4d('',(!this.U&&(this.U=eb(this)),lJ(lJ(this.U,6),221)).v))&&this.Jl((!this.U&&(this.U=eb(this)),lJ(lJ(this.U,6),221)).a)}if(a.Di(BJe)||a.Di(BRe)||a.Di(P2e)){this.Jl(sfd(this));(!this.U&&(this.U=eb(this)),lJ(lJ(this.U,6),221)).c&&((!this.U&&(this.U=eb(this)),lJ(lJ(this.U,6),221)).v==null||!(!this.U&&(this.U=eb(this)),lJ(lJ(this.U,6),221)).v.length)&&!(!this.U&&(this.U=eb(this)),lJ(lJ(this.U,6),221)).J.length?Sc(this.Bc(),k3e):$c(this.Bc(),k3e)}};var pbb=w2d(U2e,'AbstractColorPickerConnector',801);asb(221,6,{6:1,39:1,221:1,3:1},_Pd);_.a=null;_.b=false;_.c=false;var rib=w2d(J$e,'ColorPickerState',221);EBe(ep)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
