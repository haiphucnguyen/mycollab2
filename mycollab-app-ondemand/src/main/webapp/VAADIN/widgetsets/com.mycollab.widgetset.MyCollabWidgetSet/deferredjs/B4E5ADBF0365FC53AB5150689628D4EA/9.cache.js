$wnd.com_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function nIb(a){return a.g}\nfunction pIb(a,b){jHb(a,b);--a.i}\nfunction qrd(){Od.call(this)}\nfunction o2d(){Se.call(this);this.H=eof}\nfunction fw(a){return (xt(),wt).Ee(a,'col')}\nfunction RAc(a,b){R9b(a.a,new WKc(new kLc(Amb),'openPopup'),AK(wK(vqb,1),vVe,1,3,[(Mhe(),b?Lhe:Khe)]))}\nfunction oIb(a,b){if(b<0){throw new Ghe('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new Ghe(b_e+b+c_e+a.i)}}\nfunction rIb(a,b){if(a.i==b){return}if(b<0){throw new Ghe('Cannot set number of rows to '+b)}if(a.i<b){tIb((_Ab(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){pIb(a,a.i-1)}}}\nfunction sIb(a,b){TGb();qHb.call(this);kHb(this,new MHb(this));nHb(this,new gJb(this));lHb(this,new WIb(this));qIb(this,b);rIb(this,a)}\nfunction VIb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){ls(a.a,fw($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){us(a.a,a.a.lastChild)}}}\nfunction tIb(a,b,c){var d=$doc.createElement('td');d.innerHTML=f2e;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction prd(a){if((!a.db&&(a.db=Tc(a)),HK(HK(a.db,6),230)).c&&((!a.db&&(a.db=Tc(a)),HK(HK(a.db,6),230)).w==null||qke('',(!a.db&&(a.db=Tc(a)),HK(HK(a.db,6),230)).w))){return (!a.db&&(a.db=Tc(a)),HK(HK(a.db,6),230)).a}return (!a.db&&(a.db=Tc(a)),HK(HK(a.db,6),230)).w}\nfunction qIb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new Ghe('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){VGb(a,c,d);e=XGb(a,c,d,false);f=cJb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){fHb(a,c,d)}}}a.g=b;VIb(a.I,b,false)}\nvar $nf='showDefaultCaption',_nf='setColor',aof='setOpen',bof='background',cof={47:1,7:1,13:1,27:1,32:1,34:1,33:1,31:1,3:1},dof='com.vaadin.client.ui.colorpicker',eof='v-colorpicker',wof='v-default-caption-width';$wb(1,null,{});_.gC=function X(){return this.cZ};$wb(133,9,U$e);_.wf=function LEb(a){return Tb(this,a,(FD(),FD(),ED))};$wb(854,28,d_e);_.wf=function rHb(a){return Tb(this,a,(FD(),FD(),ED))};$wb(277,45,f_e);_.wf=function $Hb(a){return Tb(this,a,(FD(),FD(),ED))};$wb(672,854,d_e,sIb);_.Kg=function uIb(){var a;a=(_Ab(),Hw($doc));ft(a,f2e);return a};_.Lg=function vIb(a){return nIb(this)};_.Mg=function wIb(){return this.i};_.Ng=function xIb(a,b){oIb(this,a);if(b<0){throw new Ghe('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new Ghe(_$e+b+a_e+this.g)}};_.Og=function yIb(a){oIb(this,a)};_.g=0;_.i=0;var QS=Aie(UUe,'Grid',672);$wb(113,168,i_e);_.wf=function EIb(a){return Tb(this,a,(FD(),FD(),ED))};$wb(400,9,t_e);_.wf=function EJb(a){return Ub(this,a,(FD(),FD(),ED))};$wb(840,34,cof);_.cd=function rrd(){return false};_.fd=function srd(){return !this.db&&(this.db=Tc(this)),HK(HK(this.db,6),230)};_.Tc=function trd(){return !this.db&&(this.db=Tc(this)),HK(HK(this.db,6),230)};_.Vc=function urd(){LK(this.hd(),55)&&HK(this.hd(),55).wf(this)};_.Xc=function vrd(a){Gd(this,a);if(a.Zi(V1e)){this.hm();(!this.db&&(this.db=Tc(this)),HK(HK(this.db,6),230)).c&&((!this.db&&(this.db=Tc(this)),HK(HK(this.db,6),230)).w==null||qke('',(!this.db&&(this.db=Tc(this)),HK(HK(this.db,6),230)).w))&&this.im((!this.db&&(this.db=Tc(this)),HK(HK(this.db,6),230)).a)}if(a.Zi(k2e)||a.Zi(L9e)||a.Zi($nf)){this.im(prd(this));(!this.db&&(this.db=Tc(this)),HK(HK(this.db,6),230)).c&&((!this.db&&(this.db=Tc(this)),HK(HK(this.db,6),230)).w==null||!(!this.db&&(this.db=Tc(this)),HK(HK(this.db,6),230)).w.length)&&!(!this.db&&(this.db=Tc(this)),HK(HK(this.db,6),230)).K.length?this.hd().fc(wof):this.hd().kc(wof)}};var Teb=Aie(dof,'AbstractColorPickerConnector',840);$wb(230,6,{6:1,38:1,230:1,3:1},o2d);_.a=null;_.b=false;_.c=false;var Bmb=Aie(mjf,'ColorPickerState',230);BUe(rq)(9);\n//# sourceURL=com.mycollab.widgetset.MyCollabWidgetSet-9.js\n")