$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function oLb(a){return a.g}\nfunction qLb(a,b){jKb(a,b);--a.i}\nfunction YCd(){Od.call(this)}\nfunction bde(){Re.call(this);this.H=Lvf}\nfunction dw(a){return (st(),rt).Ee(a,'col')}\nfunction aNc(a,b){Olc(a.a,new OWc(new cXc(cqb),'openPopup'),bN(ZM(Ytb,1),T0e,1,3,[(xse(),b?wse:vse)]))}\nfunction pLb(a,b){if(b<0){throw new rse('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new rse($5e+b+_5e+a.i)}}\nfunction sLb(a,b){if(a.i==b){return}if(b<0){throw new rse('Cannot set number of rows to '+b)}if(a.i<b){uLb((YDb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){qLb(a,a.i-1)}}}\nfunction tLb(a,b){TJb();qKb.call(this);kKb(this,new MKb(this));nKb(this,new hMb(this));lKb(this,new XLb(this));rLb(this,b);sLb(this,a)}\nfunction WLb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){gs(a.a,dw($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){ps(a.a,a.a.lastChild)}}}\nfunction uLb(a,b,c){var d=$doc.createElement('td');d.innerHTML=Iaf;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction XCd(a){if((!a.db&&(a.db=Tc(a)),iN(iN(a.db,6),230)).c&&((!a.db&&(a.db=Tc(a)),iN(iN(a.db,6),230)).w==null||ive('',(!a.db&&(a.db=Tc(a)),iN(iN(a.db,6),230)).w))){return (!a.db&&(a.db=Tc(a)),iN(iN(a.db,6),230)).a}return (!a.db&&(a.db=Tc(a)),iN(iN(a.db,6),230)).w}\nfunction rLb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new rse('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){VJb(a,c,d);e=XJb(a,c,d,false);f=dMb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){fKb(a,c,d)}}}a.g=b;WLb(a.I,b,false)}\nvar Fvf='showDefaultCaption',Gvf='setColor',Hvf='setOpen',Ivf='background',Jvf={52:1,7:1,13:1,28:1,32:1,35:1,34:1,31:1,3:1},Kvf='com.vaadin.client.ui.colorpicker',Lvf='v-colorpicker',bwf='v-default-caption-width';Xzb(1,null,{});_.gC=function X(){return this.cZ};Xzb(136,9,R5e);_.xf=function JHb(a){return Ub(this,a,(CD(),CD(),BD))};Xzb(866,30,a6e);_.xf=function rKb(a){return Ub(this,a,(CD(),CD(),BD))};Xzb(265,48,c6e);_.xf=function $Kb(a){return Ub(this,a,(CD(),CD(),BD))};Xzb(675,866,a6e,tLb);_.ih=function vLb(){var a;a=(YDb(),Fw($doc));at(a,Iaf);return a};_.jh=function wLb(a){return oLb(this)};_.kh=function xLb(){return this.i};_.lh=function yLb(a,b){pLb(this,a);if(b<0){throw new rse('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new rse(Y5e+b+Z5e+this.g)}};_.mh=function zLb(a){pLb(this,a)};_.g=0;_.i=0;var CV=nte(U_e,'Grid',675);Xzb(55,170,g6e);_.xf=function FLb(a){return Ub(this,a,(CD(),CD(),BD))};Xzb(402,9,r6e);_.xf=function GMb(a){return Vb(this,a,(CD(),CD(),BD))};Xzb(852,35,Jvf);_.cd=function ZCd(){return false};_.fd=function $Cd(){return !this.db&&(this.db=Tc(this)),iN(iN(this.db,6),230)};_.Tc=function _Cd(){return !this.db&&(this.db=Tc(this)),iN(iN(this.db,6),230)};_.Vc=function aDd(){mN(this.hd(),58)&&iN(this.hd(),58).xf(this)};_.Xc=function bDd(a){Gd(this,a);if(a.fk(b9e)){this.nn();(!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),230)).c&&((!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),230)).w==null||ive('',(!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),230)).w))&&this.pn((!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),230)).a)}if(a.fk(X8e)||a.fk(jif)||a.fk(Fvf)){this.pn(XCd(this));(!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),230)).c&&((!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),230)).w==null||!(!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),230)).w.length)&&!(!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),230)).K.length?this.hd().fc(bwf):this.hd().kc(bwf)}};var Hib=nte(Kvf,'AbstractColorPickerConnector',852);Xzb(230,6,{6:1,40:1,230:1,3:1},bde);_.a=null;_.b=false;_.c=false;var dqb=nte(qrf,'ColorPickerState',230);A_e(lq)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
