$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function QKb(a){return a.g}\nfunction SKb(a,b){YJb(a,b);--a.i}\nfunction Ovd(){ve.call(this)}\nfunction d4d(){mf.call(this);this.G=hmf}\nfunction ly(a){return (Av(),zv).Te(a,'col')}\nfunction LGc(a,b){Dhc(a.a,new wQc(new MQc(mqb),'openPopup'),cP($O(Otb,1),KTe,1,3,[(Xhe(),b?Whe:Vhe)]))}\nfunction RKb(a,b){if(b<0){throw new Rhe('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new Rhe(fZe+b+gZe+a.i)}}\nfunction UKb(a,b){if(a.i==b){return}if(b<0){throw new Rhe('Cannot set number of rows to '+b)}if(a.i<b){WKb((hEb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){SKb(a,a.i-1)}}}\nfunction VKb(a,b){GJb();dKb.call(this);ZJb(this,new zKb(this));aKb(this,new JLb(this));$Jb(this,new xLb(this));TKb(this,b);UKb(this,a)}\nfunction wLb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){ou(a.a,ly($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){xu(a.a,a.a.lastChild)}}}\nfunction WKb(a,b,c){var d=$doc.createElement('td');d.innerHTML=E1e;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction Nvd(a){if((!a.U&&(a.U=Fd(a)),jP(jP(a.U,6),224)).c&&((!a.U&&(a.U=Fd(a)),jP(jP(a.U,6),224)).v==null||Gke('',(!a.U&&(a.U=Fd(a)),jP(jP(a.U,6),224)).v))){return (!a.U&&(a.U=Fd(a)),jP(jP(a.U,6),224)).a}return (!a.U&&(a.U=Fd(a)),jP(jP(a.U,6),224)).v}\nfunction TKb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new Rhe('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){IJb(a,c,d);e=KJb(a,c,d,false);f=FLb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){UJb(a,c,d)}}}a.g=b;wLb(a.I,b,false)}\nvar rlf={51:1,7:1,16:1,30:1,33:1,35:1,34:1,32:1,3:1},slf='com.vaadin.client.ui.colorpicker',tlf='background',emf='showDefaultCaption',fmf='setOpen',gmf='setColor',hmf='v-colorpicker',pmf='v-default-caption-width';lAb(1,null,{});_.gC=function X(){return this.cZ};lAb(124,9,kUe);_.re=function $k(a){return Sb(this,a,(JF(),JF(),IF))};lAb(838,29,hZe);_.re=function eKb(a){return Sb(this,a,(JF(),JF(),IF))};lAb(253,50,jZe);_.re=function MKb(a){return Sb(this,a,(JF(),JF(),IF))};lAb(663,838,hZe,VKb);_.oh=function XKb(){var a;a=(hEb(),Ny($doc));iv(a,E1e);return a};_.ph=function YKb(a){return QKb(this)};_.qh=function ZKb(){return this.i};_.rh=function $Kb(a,b){RKb(this,a);if(b<0){throw new Rhe('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new Rhe(dZe+b+eZe+this.g)}};_.sh=function _Kb(a){RKb(this,a)};_.g=0;_.i=0;var tX=Mie(SSe,'Grid',663);lAb(53,612,kZe);_.re=function fLb(a){return Sb(this,a,(JF(),JF(),IF))};lAb(389,9,vZe);_.re=function hMb(a){return Tb(this,a,(JF(),JF(),IF))};lAb(830,35,rlf);_.Wc=function Pvd(){return false};_.Zc=function Qvd(){return !this.U&&(this.U=Fd(this)),jP(jP(this.U,6),224)};_.Lc=function Rvd(){return !this.U&&(this.U=Fd(this)),jP(jP(this.U,6),224)};_.Nc=function Svd(){nP(this._c(),54)&&jP(this._c(),54).re(this)};_.Pc=function Tvd(a){oe(this,a);if(a._j(L_e)){this.Sm();(!this.U&&(this.U=Fd(this)),jP(jP(this.U,6),224)).c&&((!this.U&&(this.U=Fd(this)),jP(jP(this.U,6),224)).v==null||Gke('',(!this.U&&(this.U=Fd(this)),jP(jP(this.U,6),224)).v))&&this.Tm((!this.U&&(this.U=Fd(this)),jP(jP(this.U,6),224)).a)}if(a._j(F_e)||a._j(G9e)||a._j(emf)){this.Tm(Nvd(this));(!this.U&&(this.U=Fd(this)),jP(jP(this.U,6),224)).c&&((!this.U&&(this.U=Fd(this)),jP(jP(this.U,6),224)).v==null||!(!this.U&&(this.U=Fd(this)),jP(jP(this.U,6),224)).v.length)&&!(!this.U&&(this.U=Fd(this)),jP(jP(this.U,6),224)).J.length?db(this._c(),pmf):lb(this._c(),pmf)}};var ojb=Mie(slf,'AbstractColorPickerConnector',830);lAb(224,6,{6:1,41:1,224:1,3:1},d4d);_.a=null;_.b=false;_.c=false;var nqb=Mie(Jhf,'ColorPickerState',224);ySe(ts)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
