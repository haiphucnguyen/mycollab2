$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function MGb(a){return a.g}\nfunction OGb(a,b){HFb(a,b);--a.i}\nfunction Lod(){Nd.call(this)}\nfunction Q$d(){Qe.call(this);this.H=vff}\nfunction Sv(a){return (it(),ht).Ee(a,'col')}\nfunction Kyc(a,b){e8b(a.a,new AIc(new QIc(Elb),'openPopup'),iK(eK(ypb,1),pOe,1,3,[(kee(),b?jee:iee)]))}\nfunction NGb(a,b){if(b<0){throw new eee('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new eee(qTe+b+rTe+a.i)}}\nfunction QGb(a,b){if(a.i==b){return}if(b<0){throw new eee('Cannot set number of rows to '+b)}if(a.i<b){SGb((xzb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){OGb(a,a.i-1)}}}\nfunction RGb(a,b){pFb();OFb.call(this);IFb(this,new iGb(this));LFb(this,new FHb(this));JFb(this,new tHb(this));PGb(this,b);QGb(this,a)}\nfunction sHb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){Yr(a.a,Sv($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){fs(a.a,a.a.lastChild)}}}\nfunction SGb(a,b,c){var d=$doc.createElement('td');d.innerHTML=wWe;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction Kod(a){if((!a.db&&(a.db=Sc(a)),pK(pK(a.db,6),223)).c&&((!a.db&&(a.db=Sc(a)),pK(pK(a.db,6),223)).w==null||Pge('',(!a.db&&(a.db=Sc(a)),pK(pK(a.db,6),223)).w))){return (!a.db&&(a.db=Sc(a)),pK(pK(a.db,6),223)).a}return (!a.db&&(a.db=Sc(a)),pK(pK(a.db,6),223)).w}\nfunction PGb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new eee('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){rFb(a,c,d);e=tFb(a,c,d,false);f=BHb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){DFb(a,c,d)}}}a.g=b;sHb(a.I,b,false)}\nvar pff='showDefaultCaption',qff='setColor',rff='setOpen',sff='background',tff={47:1,7:1,11:1,26:1,30:1,33:1,32:1,29:1,3:1},uff='com.vaadin.client.ui.colorpicker',vff='v-colorpicker',Nff='v-default-caption-width';wvb(1,null,{});_.gC=function X(){return this.cZ};wvb(137,9,hTe);_.wf=function hDb(a){return Tb(this,a,(nD(),nD(),mD))};wvb(845,27,sTe);_.wf=function PFb(a){return Tb(this,a,(nD(),nD(),mD))};wvb(268,44,uTe);_.wf=function wGb(a){return Tb(this,a,(nD(),nD(),mD))};wvb(658,845,sTe,RGb);_.Jg=function TGb(){var a;a=(xzb(),sw($doc));Ss(a,wWe);return a};_.Kg=function UGb(a){return MGb(this)};_.Lg=function VGb(){return this.i};_.Mg=function WGb(a,b){NGb(this,a);if(b<0){throw new eee('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new eee(oTe+b+pTe+this.g)}};_.Ng=function XGb(a){NGb(this,a)};_.g=0;_.i=0;var xS=$ee(rNe,'Grid',658);wvb(111,169,yTe);_.wf=function bHb(a){return Tb(this,a,(nD(),nD(),mD))};wvb(392,9,JTe);_.wf=function bIb(a){return Ub(this,a,(nD(),nD(),mD))};wvb(831,33,tff);_.cd=function Mod(){return false};_.fd=function Nod(){return !this.db&&(this.db=Sc(this)),pK(pK(this.db,6),223)};_.Tc=function Ood(){return !this.db&&(this.db=Sc(this)),pK(pK(this.db,6),223)};_.Vc=function Pod(){tK(this.hd(),53)&&pK(this.hd(),53).wf(this)};_.Xc=function Qod(a){Fd(this,a);if(a.Yi(kWe)){this.em();(!this.db&&(this.db=Sc(this)),pK(pK(this.db,6),223)).c&&((!this.db&&(this.db=Sc(this)),pK(pK(this.db,6),223)).w==null||Pge('',(!this.db&&(this.db=Sc(this)),pK(pK(this.db,6),223)).w))&&this.fm((!this.db&&(this.db=Sc(this)),pK(pK(this.db,6),223)).a)}if(a.Yi(BWe)||a.Yi(R1e)||a.Yi(pff)){this.fm(Kod(this));(!this.db&&(this.db=Sc(this)),pK(pK(this.db,6),223)).c&&((!this.db&&(this.db=Sc(this)),pK(pK(this.db,6),223)).w==null||!(!this.db&&(this.db=Sc(this)),pK(pK(this.db,6),223)).w.length)&&!(!this.db&&(this.db=Sc(this)),pK(pK(this.db,6),223)).K.length?this.hd().fc(Nff):this.hd().kc(Nff)}};var heb=$ee(uff,'AbstractColorPickerConnector',831);wvb(223,6,{6:1,38:1,223:1,3:1},Q$d);_.a=null;_.b=false;_.c=false;var Flb=$ee(abf,'ColorPickerState',223);$Me(cq)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
