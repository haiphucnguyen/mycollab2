$wnd.com_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function SEb(a){return a.g}\nfunction UEb(a,b){ODb(a,b);--a.i}\nfunction xrd(){Od.call(this)}\nfunction x2d(){Se.call(this);this.H=iof}\nfunction Xr(a){return (np(),mp).ge(a,'col')}\nfunction WAc(a,b){Y9b(a.a,new _Kc(new pLc(uib),'openPopup'),tG(pG(pmb,1),GVe,1,3,[(Vhe(),b?Uhe:The)]))}\nfunction TEb(a,b){if(b<0){throw new Phe('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new Phe(W$e+b+X$e+a.i)}}\nfunction WEb(a,b){if(a.i==b){return}if(b<0){throw new Phe('Cannot set number of rows to '+b)}if(a.i<b){YEb((Wwb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){UEb(a,a.i-1)}}}\nfunction XEb(a,b){wDb();VDb.call(this);PDb(this,new pEb(this));SDb(this,new LFb(this));QDb(this,new zFb(this));VEb(this,b);WEb(this,a)}\nfunction yFb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){ao(a.a,Xr($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){ko(a.a,a.a.lastChild)}}}\nfunction YEb(a,b,c){var d=$doc.createElement('td');d.innerHTML=p2e;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction wrd(a){if((!a.db&&(a.db=Tc(a)),AG(AG(a.db,6),230)).c&&((!a.db&&(a.db=Tc(a)),AG(AG(a.db,6),230)).w==null||zke('',(!a.db&&(a.db=Tc(a)),AG(AG(a.db,6),230)).w))){return (!a.db&&(a.db=Tc(a)),AG(AG(a.db,6),230)).a}return (!a.db&&(a.db=Tc(a)),AG(AG(a.db,6),230)).w}\nfunction VEb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new Phe('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){yDb(a,c,d);e=ADb(a,c,d,false);f=HFb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){KDb(a,c,d)}}}a.g=b;yFb(a.I,b,false)}\nvar cof='showDefaultCaption',dof='setColor',eof='setOpen',fof='background',gof={46:1,7:1,12:1,26:1,31:1,34:1,33:1,30:1,3:1},hof='com.vaadin.client.ui.colorpicker',iof='v-colorpicker',Aof='v-default-caption-width';Usb(1,null,{});_.gC=function X(){return this.cZ};Usb(141,9,N$e);_.cf=function kBb(a){return Tb(this,a,(Az(),Az(),zz))};Usb(857,28,Y$e);_.cf=function WDb(a){return Tb(this,a,(Az(),Az(),zz))};Usb(276,45,$$e);_.cf=function DEb(a){return Tb(this,a,(Az(),Az(),zz))};Usb(674,857,Y$e,XEb);_.Eg=function ZEb(){var a;a=(Wwb(),xs($doc));Xo(a,p2e);return a};_.Fg=function $Eb(a){return SEb(this)};_.Gg=function _Eb(){return this.i};_.Hg=function aFb(a,b){TEb(this,a);if(b<0){throw new Phe('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new Phe(U$e+b+V$e+this.g)}};_.Ig=function bFb(a){TEb(this,a)};_.g=0;_.i=0;var dO=Jie(dVe,'Grid',674);Usb(113,168,b_e);_.cf=function hFb(a){return Tb(this,a,(Az(),Az(),zz))};Usb(400,9,m_e);_.cf=function hGb(a){return Ub(this,a,(Az(),Az(),zz))};Usb(843,34,gof);_.cd=function yrd(){return false};_.fd=function zrd(){return !this.db&&(this.db=Tc(this)),AG(AG(this.db,6),230)};_.Tc=function Ard(){return !this.db&&(this.db=Tc(this)),AG(AG(this.db,6),230)};_.Vc=function Brd(){EG(this.hd(),54)&&AG(this.hd(),54).cf(this)};_.Xc=function Crd(a){Gd(this,a);if(a.$i(d2e)){this.jm();(!this.db&&(this.db=Tc(this)),AG(AG(this.db,6),230)).c&&((!this.db&&(this.db=Tc(this)),AG(AG(this.db,6),230)).w==null||zke('',(!this.db&&(this.db=Tc(this)),AG(AG(this.db,6),230)).w))&&this.km((!this.db&&(this.db=Tc(this)),AG(AG(this.db,6),230)).a)}if(a.$i(u2e)||a.$i(S9e)||a.$i(cof)){this.km(wrd(this));(!this.db&&(this.db=Tc(this)),AG(AG(this.db,6),230)).c&&((!this.db&&(this.db=Tc(this)),AG(AG(this.db,6),230)).w==null||!(!this.db&&(this.db=Tc(this)),AG(AG(this.db,6),230)).w.length)&&!(!this.db&&(this.db=Tc(this)),AG(AG(this.db,6),230)).K.length?this.hd().fc(Aof):this.hd().kc(Aof)}};var Nab=Jie(hof,'AbstractColorPickerConnector',843);Usb(230,6,{6:1,39:1,230:1,3:1},x2d);_.a=null;_.b=false;_.c=false;var vib=Jie(ujf,'ColorPickerState',230);MUe(fm)(9);\n//# sourceURL=com.mycollab.widgetset.MyCollabWidgetSet-9.js\n")