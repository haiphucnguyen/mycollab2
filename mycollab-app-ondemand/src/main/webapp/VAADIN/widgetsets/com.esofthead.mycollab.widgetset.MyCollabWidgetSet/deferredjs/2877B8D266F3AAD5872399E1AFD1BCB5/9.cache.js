$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function qLb(a){return a.g}\nfunction sLb(a,b){lKb(a,b);--a.i}\nfunction fCd(){Od.call(this)}\nfunction pce(){Re.call(this);this.H=Ewf}\nfunction dw(a){return (st(),rt).Ee(a,'col')}\nfunction $Kc(a,b){Ojc(a.a,new OUc(new cVc(Qpb),'openPopup'),bN(ZM(Jtb,1),K1e,1,3,[(Lre(),b?Kre:Jre)]))}\nfunction rLb(a,b){if(b<0){throw new Fre('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new Fre(R6e+b+S6e+a.i)}}\nfunction uLb(a,b){if(a.i==b){return}if(b<0){throw new Fre('Cannot set number of rows to '+b)}if(a.i<b){wLb((ZDb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){sLb(a,a.i-1)}}}\nfunction vLb(a,b){VJb();sKb.call(this);mKb(this,new OKb(this));pKb(this,new jMb(this));nKb(this,new ZLb(this));tLb(this,b);uLb(this,a)}\nfunction YLb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){gs(a.a,dw($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){ps(a.a,a.a.lastChild)}}}\nfunction wLb(a,b,c){var d=$doc.createElement('td');d.innerHTML=$af;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction eCd(a){if((!a.db&&(a.db=Tc(a)),iN(iN(a.db,6),229)).c&&((!a.db&&(a.db=Tc(a)),iN(iN(a.db,6),229)).w==null||wue('',(!a.db&&(a.db=Tc(a)),iN(iN(a.db,6),229)).w))){return (!a.db&&(a.db=Tc(a)),iN(iN(a.db,6),229)).a}return (!a.db&&(a.db=Tc(a)),iN(iN(a.db,6),229)).w}\nfunction tLb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new Fre('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){XJb(a,c,d);e=ZJb(a,c,d,false);f=fMb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){hKb(a,c,d)}}}a.g=b;YLb(a.I,b,false)}\nvar ywf='showDefaultCaption',zwf='setColor',Awf='setOpen',Bwf='background',Cwf={52:1,7:1,13:1,28:1,32:1,35:1,34:1,31:1,3:1},Dwf='com.vaadin.client.ui.colorpicker',Ewf='v-colorpicker',Wwf='v-default-caption-width';Yzb(1,null,{});_.gC=function X(){return this.cZ};Yzb(135,9,I6e);_.xf=function LHb(a){return Ub(this,a,(CD(),CD(),BD))};Yzb(864,30,T6e);_.xf=function tKb(a){return Ub(this,a,(CD(),CD(),BD))};Yzb(264,50,V6e);_.xf=function aLb(a){return Ub(this,a,(CD(),CD(),BD))};Yzb(677,864,T6e,vLb);_.ih=function xLb(){var a;a=(ZDb(),Fw($doc));at(a,$af);return a};_.jh=function yLb(a){return qLb(this)};_.kh=function zLb(){return this.i};_.lh=function ALb(a,b){rLb(this,a);if(b<0){throw new Fre('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new Fre(P6e+b+Q6e+this.g)}};_.mh=function BLb(a){rLb(this,a)};_.g=0;_.i=0;var CV=Bse(M0e,'Grid',677);Yzb(55,623,Z6e);_.xf=function HLb(a){return Ub(this,a,(CD(),CD(),BD))};Yzb(402,9,i7e);_.xf=function IMb(a){return Vb(this,a,(CD(),CD(),BD))};Yzb(850,35,Cwf);_.cd=function gCd(){return false};_.fd=function hCd(){return !this.db&&(this.db=Tc(this)),iN(iN(this.db,6),229)};_.Tc=function iCd(){return !this.db&&(this.db=Tc(this)),iN(iN(this.db,6),229)};_.Vc=function jCd(){mN(this.hd(),58)&&iN(this.hd(),58).xf(this)};_.Xc=function kCd(a){Gd(this,a);if(a._j(q9e)){this.ln();(!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),229)).c&&((!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),229)).w==null||wue('',(!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),229)).w))&&this.mn((!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),229)).a)}if(a._j(k9e)||a._j(Rif)||a._j(ywf)){this.mn(eCd(this));(!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),229)).c&&((!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),229)).w==null||!(!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),229)).w.length)&&!(!this.db&&(this.db=Tc(this)),iN(iN(this.db,6),229)).K.length?this.hd().fc(Wwf):this.hd().kc(Wwf)}};var tib=Bse(Dwf,'AbstractColorPickerConnector',850);Yzb(229,6,{6:1,40:1,229:1,3:1},pce);_.a=null;_.b=false;_.c=false;var Rpb=Bse(fsf,'ColorPickerState',229);s0e(lq)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
