$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function wMb(a){return a.g}\nfunction yMb(a,b){rLb(a,b);--a.i}\nfunction nCd(){Od.call(this)}\nfunction wce(){Re.call(this);this.H=Dwf}\nfunction sx(a){return (Hu(),Gu).Ie(a,'col')}\nfunction eMc(a,b){Ukc(a.a,new UVc(new iWc(erb),'openPopup'),qO(mO($ub,1),J1e,1,3,[(Sre(),b?Rre:Qre)]))}\nfunction xMb(a,b){if(b<0){throw new Mre('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new Mre(P6e+b+Q6e+a.i)}}\nfunction AMb(a,b){if(a.i==b){return}if(b<0){throw new Mre('Cannot set number of rows to '+b)}if(a.i<b){CMb((nFb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){yMb(a,a.i-1)}}}\nfunction BMb(a,b){_Kb();yLb.call(this);sLb(this,new ULb(this));vLb(this,new pNb(this));tLb(this,new dNb(this));zMb(this,b);AMb(this,a)}\nfunction cNb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){vt(a.a,sx($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){Et(a.a,a.a.lastChild)}}}\nfunction CMb(a,b,c){var d=$doc.createElement('td');d.innerHTML=Yaf;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction mCd(a){if((!a.db&&(a.db=Tc(a)),xO(xO(a.db,6),229)).c&&((!a.db&&(a.db=Tc(a)),xO(xO(a.db,6),229)).w==null||Due('',(!a.db&&(a.db=Tc(a)),xO(xO(a.db,6),229)).w))){return (!a.db&&(a.db=Tc(a)),xO(xO(a.db,6),229)).a}return (!a.db&&(a.db=Tc(a)),xO(xO(a.db,6),229)).w}\nfunction zMb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new Mre('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){bLb(a,c,d);e=dLb(a,c,d,false);f=lNb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){nLb(a,c,d)}}}a.g=b;cNb(a.I,b,false)}\nvar xwf='showDefaultCaption',ywf='setColor',zwf='setOpen',Awf='background',Bwf={52:1,7:1,13:1,28:1,32:1,35:1,34:1,31:1,3:1},Cwf='com.vaadin.client.ui.colorpicker',Dwf='v-colorpicker',Vwf='v-default-caption-width';mBb(1,null,{});_.gC=function X(){return this.cZ};mBb(136,9,I6e);_.Bf=function _Ib(a){return Ub(this,a,(RE(),RE(),QE))};mBb(867,30,R6e);_.Bf=function zLb(a){return Ub(this,a,(RE(),RE(),QE))};mBb(263,50,T6e);_.Bf=function gMb(a){return Ub(this,a,(RE(),RE(),QE))};mBb(677,867,R6e,BMb);_.mh=function DMb(){var a;a=(nFb(),Ux($doc));pu(a,Yaf);return a};_.nh=function EMb(a){return wMb(this)};_.oh=function FMb(){return this.i};_.ph=function GMb(a,b){xMb(this,a);if(b<0){throw new Mre('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new Mre(N6e+b+O6e+this.g)}};_.qh=function HMb(a){xMb(this,a)};_.g=0;_.i=0;var SW=Ise(K0e,'Grid',677);mBb(55,623,X6e);_.Bf=function NMb(a){return Ub(this,a,(RE(),RE(),QE))};mBb(402,9,g7e);_.Bf=function ONb(a){return Vb(this,a,(RE(),RE(),QE))};mBb(853,35,Bwf);_.cd=function oCd(){return false};_.fd=function pCd(){return !this.db&&(this.db=Tc(this)),xO(xO(this.db,6),229)};_.Tc=function qCd(){return !this.db&&(this.db=Tc(this)),xO(xO(this.db,6),229)};_.Vc=function rCd(){BO(this.hd(),58)&&xO(this.hd(),58).Bf(this)};_.Xc=function sCd(a){Gd(this,a);if(a.dk(o9e)){this.pn();(!this.db&&(this.db=Tc(this)),xO(xO(this.db,6),229)).c&&((!this.db&&(this.db=Tc(this)),xO(xO(this.db,6),229)).w==null||Due('',(!this.db&&(this.db=Tc(this)),xO(xO(this.db,6),229)).w))&&this.qn((!this.db&&(this.db=Tc(this)),xO(xO(this.db,6),229)).a)}if(a.dk(i9e)||a.dk(Pif)||a.dk(xwf)){this.qn(mCd(this));(!this.db&&(this.db=Tc(this)),xO(xO(this.db,6),229)).c&&((!this.db&&(this.db=Tc(this)),xO(xO(this.db,6),229)).w==null||!(!this.db&&(this.db=Tc(this)),xO(xO(this.db,6),229)).w.length)&&!(!this.db&&(this.db=Tc(this)),xO(xO(this.db,6),229)).K.length?this.hd().fc(Vwf):this.hd().kc(Vwf)}};var Jjb=Ise(Cwf,'AbstractColorPickerConnector',853);mBb(229,6,{6:1,40:1,229:1,3:1},wce);_.a=null;_.b=false;_.c=false;var frb=Ise(dsf,'ColorPickerState',229);q0e(Ar)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")