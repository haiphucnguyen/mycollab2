$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function ZGb(a){return a.g}\nfunction _Gb(a,b){UFb(a,b);--a.i}\nfunction Cod(){Nd.call(this)}\nfunction z$d(){Qe.call(this);this.G=Ehf}\nfunction Qv(a){return (gt(),ft).Ee(a,'col')}\nfunction Rwc(a,b){k6b(a.a,new ZGc(new nHc(qlb),'openPopup'),bK(ZJ(jpb,1),kQe,1,3,[(Tde(),b?Sde:Rde)]))}\nfunction $Gb(a,b){if(b<0){throw new Nde('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new Nde(jVe+b+kVe+a.i)}}\nfunction bHb(a,b){if(a.i==b){return}if(b<0){throw new Nde('Cannot set number of rows to '+b)}if(a.i<b){dHb((Jzb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){_Gb(a,a.i-1)}}}\nfunction cHb(a,b){CFb();_Fb.call(this);VFb(this,new vGb(this));YFb(this,new SHb(this));WFb(this,new GHb(this));aHb(this,b);bHb(this,a)}\nfunction FHb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){Wr(a.a,Qv($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){ds(a.a,a.a.lastChild)}}}\nfunction dHb(a,b,c){var d=$doc.createElement('td');d.innerHTML=SXe;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction Bod(a){if((!a.db&&(a.db=Sc(a)),iK(iK(a.db,6),220)).c&&((!a.db&&(a.db=Sc(a)),iK(iK(a.db,6),220)).v==null||vge('',(!a.db&&(a.db=Sc(a)),iK(iK(a.db,6),220)).v))){return (!a.db&&(a.db=Sc(a)),iK(iK(a.db,6),220)).a}return (!a.db&&(a.db=Sc(a)),iK(iK(a.db,6),220)).v}\nfunction aHb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new Nde('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){EFb(a,c,d);e=GFb(a,c,d,false);f=OHb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){QFb(a,c,d)}}}a.g=b;FHb(a.I,b,false)}\nvar yhf='showDefaultCaption',zhf='setColor',Ahf='setOpen',Bhf='background',Chf={47:1,7:1,11:1,27:1,31:1,33:1,32:1,30:1,3:1},Dhf='com.vaadin.client.ui.colorpicker',Ehf='v-colorpicker',Whf='v-default-caption-width';Ivb(1,null,{});_.gC=function X(){return this.cZ};Ivb(133,9,aVe);_.wf=function uDb(a){return Tb(this,a,(kD(),kD(),jD))};Ivb(846,26,lVe);_.wf=function aGb(a){return Tb(this,a,(kD(),kD(),jD))};Ivb(264,44,nVe);_.wf=function JGb(a){return Tb(this,a,(kD(),kD(),jD))};Ivb(663,846,lVe,cHb);_.Jg=function eHb(){var a;a=(Jzb(),qw($doc));Qs(a,SXe);return a};_.Kg=function fHb(a){return ZGb(this)};_.Lg=function gHb(){return this.i};_.Mg=function hHb(a,b){$Gb(this,a);if(b<0){throw new Nde('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new Nde(hVe+b+iVe+this.g)}};_.Ng=function iHb(a){$Gb(this,a)};_.g=0;_.i=0;var qS=Hee(oPe,'Grid',663);Ivb(108,610,rVe);_.wf=function oHb(a){return Tb(this,a,(kD(),kD(),jD))};Ivb(393,9,CVe);_.wf=function oIb(a){return Ub(this,a,(kD(),kD(),jD))};Ivb(833,33,Chf);_.cd=function Dod(){return false};_.fd=function Eod(){return !this.db&&(this.db=Sc(this)),iK(iK(this.db,6),220)};_.Tc=function Fod(){return !this.db&&(this.db=Sc(this)),iK(iK(this.db,6),220)};_.Vc=function God(){mK(this.hd(),52)&&iK(this.hd(),52).wf(this)};_.Xc=function Hod(a){Fd(this,a);if(a.Si(GXe)){this.am();(!this.db&&(this.db=Sc(this)),iK(iK(this.db,6),220)).c&&((!this.db&&(this.db=Sc(this)),iK(iK(this.db,6),220)).v==null||vge('',(!this.db&&(this.db=Sc(this)),iK(iK(this.db,6),220)).v))&&this.bm((!this.db&&(this.db=Sc(this)),iK(iK(this.db,6),220)).a)}if(a.Si(XXe)||a.Si(D3e)||a.Si(yhf)){this.bm(Bod(this));(!this.db&&(this.db=Sc(this)),iK(iK(this.db,6),220)).c&&((!this.db&&(this.db=Sc(this)),iK(iK(this.db,6),220)).v==null||!(!this.db&&(this.db=Sc(this)),iK(iK(this.db,6),220)).v.length)&&!(!this.db&&(this.db=Sc(this)),iK(iK(this.db,6),220)).J.length?this.hd().fc(Whf):this.hd().kc(Whf)}};var _db=Hee(Dhf,'AbstractColorPickerConnector',833);Ivb(220,6,{6:1,37:1,220:1,3:1},z$d);_.a=null;_.b=false;_.c=false;var rlb=Hee(adf,'ColorPickerState',220);XOe(aq)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
