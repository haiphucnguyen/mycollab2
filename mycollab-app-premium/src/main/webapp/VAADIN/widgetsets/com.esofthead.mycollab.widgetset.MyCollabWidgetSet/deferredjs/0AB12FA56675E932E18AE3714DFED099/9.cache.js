$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function GHb(a){return a.g}\nfunction IHb(a,b){OGb(a,b);--a.i}\nfunction kqd(){ue.call(this)}\nfunction BWd(){lf.call(this);this.G=$2e}\nfunction fy(a){return (uv(),tv).Re(a,'col')}\nfunction jCc(a,b){jec(a.a,new $Kc(new oLc(Fob),'openPopup'),TO(PO(csb,1),_Be,1,3,[(b8d(),b?a8d:_7d)]))}\nfunction HHb(a,b){if(b<0){throw new X7d('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new X7d(tHe+b+uHe+a.i)}}\nfunction KHb(a,b){if(a.i==b){return}if(b<0){throw new X7d('Cannot set number of rows to '+b)}if(a.i<b){MHb((ZAb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){IHb(a,a.i-1)}}}\nfunction LHb(a,b){wGb();VGb.call(this);PGb(this,new pHb(this));SGb(this,new zIb(this));QGb(this,new nIb(this));JHb(this,b);KHb(this,a)}\nfunction mIb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){ju(a.a,fy($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){su(a.a,a.a.lastChild)}}}\nfunction MHb(a,b,c){var d=$doc.createElement('td');d.innerHTML=ILe;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction jqd(a){if((!a.T&&(a.T=Ed(a)),$O($O(a.T,6),218)).c&&((!a.T&&(a.T=Ed(a)),$O($O(a.T,6),218)).v==null||Hae('',(!a.T&&(a.T=Ed(a)),$O($O(a.T,6),218)).v))){return (!a.T&&(a.T=Ed(a)),$O($O(a.T,6),218)).a}return (!a.T&&(a.T=Ed(a)),$O($O(a.T,6),218)).v}\nfunction JHb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new X7d('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){yGb(a,c,d);e=AGb(a,c,d,false);f=vIb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){KGb(a,c,d)}}}a.g=b;mIb(a.I,b,false)}\nvar i2e={52:1,7:1,16:1,29:1,34:1,32:1,35:1,33:1,3:1},j2e='com.vaadin.client.ui.colorpicker',k2e='background',X2e='showDefaultCaption',Y2e='setOpen',Z2e='setColor',$2e='v-colorpicker',g3e='v-default-caption-width';nxb(1,null,{});_.gC=function W(){return this.cZ};nxb(118,9,ACe);_.qe=function Yk(a){return Rb(this,a,(yF(),yF(),xF))};nxb(804,30,vHe);_.qe=function WGb(a){return Rb(this,a,(yF(),yF(),xF))};nxb(243,49,xHe);_.qe=function CHb(a){return Rb(this,a,(yF(),yF(),xF))};nxb(637,804,vHe,LHb);_.mh=function NHb(){var a;a=(ZAb(),Hy($doc));cv(a,ILe);return a};_.nh=function OHb(a){return GHb(this)};_.oh=function PHb(){return this.i};_.ph=function QHb(a,b){HHb(this,a);if(b<0){throw new X7d('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new X7d(rHe+b+sHe+this.g)}};_.qh=function RHb(a){HHb(this,a)};_.g=0;_.i=0;var hX=R8d(hBe,'Grid',637);nxb(51,585,yHe);_.qe=function XHb(a){return Rb(this,a,(yF(),yF(),xF))};nxb(373,9,JHe);_.qe=function YIb(a){return Sb(this,a,(yF(),yF(),xF))};nxb(797,32,i2e);_.Wc=function lqd(){return false};_.Zc=function mqd(){return !this.T&&(this.T=Ed(this)),$O($O(this.T,6),218)};_.Lc=function nqd(){return !this.T&&(this.T=Ed(this)),$O($O(this.T,6),218)};_.Nc=function oqd(){cP(this._c(),53)&&$O(this._c(),53).qe(this)};_.Pc=function pqd(a){ne(this,a);if(a.Zj(WJe)){this.Jm();(!this.T&&(this.T=Ed(this)),$O($O(this.T,6),218)).c&&((!this.T&&(this.T=Ed(this)),$O($O(this.T,6),218)).v==null||Hae('',(!this.T&&(this.T=Ed(this)),$O($O(this.T,6),218)).v))&&this.Km((!this.T&&(this.T=Ed(this)),$O($O(this.T,6),218)).a)}if(a.Zj(QJe)||a.Zj(nTe)||a.Zj(X2e)){this.Km(jqd(this));(!this.T&&(this.T=Ed(this)),$O($O(this.T,6),218)).c&&((!this.T&&(this.T=Ed(this)),$O($O(this.T,6),218)).v==null||!(!this.T&&(this.T=Ed(this)),$O($O(this.T,6),218)).v.length)&&!(!this.T&&(this.T=Ed(this)),$O($O(this.T,6),218)).J.length?cb(this._c(),g3e):kb(this._c(),g3e)}};var oib=R8d(j2e,'AbstractColorPickerConnector',797);nxb(218,6,{6:1,41:1,218:1,3:1},BWd);_.a=null;_.b=false;_.c=false;var Gob=R8d(_$e,'ColorPickerState',218);PAe(ps)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
