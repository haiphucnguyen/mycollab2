$wnd.com_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function EHb(a){return a.g}\nfunction GHb(a,b){zGb(a,b);--a.i}\nfunction dqd(){Nd.call(this)}\nfunction m0d(){Qe.call(this);this.H=Glf}\nfunction Sv(a){return (it(),ht).Ee(a,'col')}\nfunction Wzc(a,b){b9b(a.a,new RJc(new fKc(Rlb),'openPopup'),jK(fK(Lpb,1),KTe,1,3,[(Ife(),b?Hfe:Gfe)]))}\nfunction FHb(a,b){if(b<0){throw new Cfe('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new Cfe(LYe+b+MYe+a.i)}}\nfunction IHb(a,b){if(a.i==b){return}if(b<0){throw new Cfe('Cannot set number of rows to '+b)}if(a.i<b){KHb((pAb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){GHb(a,a.i-1)}}}\nfunction JHb(a,b){hGb();GGb.call(this);AGb(this,new aHb(this));DGb(this,new xIb(this));BGb(this,new lIb(this));HHb(this,b);IHb(this,a)}\nfunction kIb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){Yr(a.a,Sv($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){fs(a.a,a.a.lastChild)}}}\nfunction KHb(a,b,c){var d=$doc.createElement('td');d.innerHTML=R_e;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction cqd(a){if((!a.db&&(a.db=Sc(a)),qK(qK(a.db,6),229)).c&&((!a.db&&(a.db=Sc(a)),qK(qK(a.db,6),229)).w==null||mie('',(!a.db&&(a.db=Sc(a)),qK(qK(a.db,6),229)).w))){return (!a.db&&(a.db=Sc(a)),qK(qK(a.db,6),229)).a}return (!a.db&&(a.db=Sc(a)),qK(qK(a.db,6),229)).w}\nfunction HHb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new Cfe('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){jGb(a,c,d);e=lGb(a,c,d,false);f=tIb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){vGb(a,c,d)}}}a.g=b;kIb(a.I,b,false)}\nvar Alf='showDefaultCaption',Blf='setColor',Clf='setOpen',Dlf='background',Elf={47:1,7:1,13:1,26:1,32:1,34:1,33:1,31:1,3:1},Flf='com.vaadin.client.ui.colorpicker',Glf='v-colorpicker',Ylf='v-default-caption-width';owb(1,null,{});_.gC=function X(){return this.cZ};owb(133,9,CYe);_.wf=function _Db(a){return Tb(this,a,(oD(),oD(),nD))};owb(849,28,NYe);_.wf=function HGb(a){return Tb(this,a,(oD(),oD(),nD))};owb(277,45,PYe);_.wf=function oHb(a){return Tb(this,a,(oD(),oD(),nD))};owb(667,849,NYe,JHb);_.Jg=function LHb(){var a;a=(pAb(),sw($doc));Ss(a,R_e);return a};_.Kg=function MHb(a){return EHb(this)};_.Lg=function NHb(){return this.i};_.Mg=function OHb(a,b){FHb(this,a);if(b<0){throw new Cfe('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new Cfe(JYe+b+KYe+this.g)}};_.Ng=function PHb(a){FHb(this,a)};_.g=0;_.i=0;var yS=wge(MSe,'Grid',667);owb(113,167,TYe);_.wf=function VHb(a){return Tb(this,a,(oD(),oD(),nD))};owb(399,9,cZe);_.wf=function VIb(a){return Ub(this,a,(oD(),oD(),nD))};owb(835,34,Elf);_.cd=function eqd(){return false};_.fd=function fqd(){return !this.db&&(this.db=Sc(this)),qK(qK(this.db,6),229)};_.Tc=function gqd(){return !this.db&&(this.db=Sc(this)),qK(qK(this.db,6),229)};_.Vc=function hqd(){uK(this.hd(),55)&&qK(this.hd(),55).wf(this)};_.Xc=function iqd(a){Fd(this,a);if(a.Yi(F_e)){this.fm();(!this.db&&(this.db=Sc(this)),qK(qK(this.db,6),229)).c&&((!this.db&&(this.db=Sc(this)),qK(qK(this.db,6),229)).w==null||mie('',(!this.db&&(this.db=Sc(this)),qK(qK(this.db,6),229)).w))&&this.gm((!this.db&&(this.db=Sc(this)),qK(qK(this.db,6),229)).a)}if(a.Yi(W_e)||a.Yi(p7e)||a.Yi(Alf)){this.gm(cqd(this));(!this.db&&(this.db=Sc(this)),qK(qK(this.db,6),229)).c&&((!this.db&&(this.db=Sc(this)),qK(qK(this.db,6),229)).w==null||!(!this.db&&(this.db=Sc(this)),qK(qK(this.db,6),229)).w.length)&&!(!this.db&&(this.db=Sc(this)),qK(qK(this.db,6),229)).K.length?this.hd().fc(Ylf):this.hd().kc(Ylf)}};var seb=wge(Flf,'AbstractColorPickerConnector',835);owb(229,6,{6:1,38:1,229:1,3:1},m0d);_.a=null;_.b=false;_.c=false;var Slb=wge(Rgf,'ColorPickerState',229);tSe(cq)(9);\n//# sourceURL=com.mycollab.widgetset.MyCollabWidgetSet-9.js\n")