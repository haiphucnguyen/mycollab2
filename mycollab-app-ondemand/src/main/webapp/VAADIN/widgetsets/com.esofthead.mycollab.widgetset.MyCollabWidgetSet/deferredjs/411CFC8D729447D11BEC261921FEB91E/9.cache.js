$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function yIb(a){return a.g}\nfunction AIb(a,b){GHb(a,b);--a.i}\nfunction Ntd(){we.call(this)}\nfunction n2d(){nf.call(this);this.G=ljf}\nfunction uw(a){return (Jt(),It).ue(a,'col')}\nfunction nEc(a,b){tfc(a.a,new BNc(new RNc(eob),'openPopup'),mN(iN(Erb,1),pRe,1,3,[(Xfe(),b?Wfe:Vfe)]))}\nfunction zIb(a,b){if(b<0){throw new Rfe('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new Rfe(tWe+b+uWe+a.i)}}\nfunction CIb(a,b){if(a.i==b){return}if(b<0){throw new Rfe('Cannot set number of rows to '+b)}if(a.i<b){EIb((CBb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){AIb(a,a.i-1)}}}\nfunction DIb(a,b){oHb();NHb.call(this);HHb(this,new hIb(this));KHb(this,new rJb(this));IHb(this,new fJb(this));BIb(this,b);CIb(this,a)}\nfunction eJb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){xs(a.a,uw($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){Gs(a.a,a.a.lastChild)}}}\nfunction EIb(a,b,c){var d=$doc.createElement('td');d.innerHTML=R$e;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction Mtd(a){if((!a.U&&(a.U=Fd(a)),tN(tN(a.U,6),227)).c&&((!a.U&&(a.U=Fd(a)),tN(tN(a.U,6),227)).v==null||Gie('',(!a.U&&(a.U=Fd(a)),tN(tN(a.U,6),227)).v))){return (!a.U&&(a.U=Fd(a)),tN(tN(a.U,6),227)).a}return (!a.U&&(a.U=Fd(a)),tN(tN(a.U,6),227)).v}\nfunction BIb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new Rfe('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){qHb(a,c,d);e=sHb(a,c,d,false);f=nJb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){CHb(a,c,d)}}}a.g=b;eJb(a.I,b,false)}\nvar fjf='showDefaultCaption',gjf='setColor',hjf='setOpen',ijf='background',jjf={50:1,7:1,13:1,28:1,33:1,35:1,34:1,32:1,3:1},kjf='com.vaadin.client.ui.colorpicker',ljf='v-colorpicker',Cjf='v-default-caption-width';Sxb(1,null,{});_.gC=function X(){return this.cZ};Sxb(129,9,lWe);_.of=function oFb(a){return Sb(this,a,(RD(),RD(),QD))};Sxb(834,29,vWe);_.of=function OHb(a){return Sb(this,a,(RD(),RD(),QD))};Sxb(257,52,xWe);_.of=function uIb(a){return Sb(this,a,(RD(),RD(),QD))};Sxb(659,834,vWe,DIb);_.Zg=function FIb(){var a;a=(CBb(),Ww($doc));rt(a,R$e);return a};_.$g=function GIb(a){return yIb(this)};_._g=function HIb(){return this.i};_.ah=function IIb(a,b){zIb(this,a);if(b<0){throw new Rfe('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new Rfe(rWe+b+sWe+this.g)}};_.bh=function JIb(a){zIb(this,a)};_.g=0;_.i=0;var zV=Mge(yQe,'Grid',659);Sxb(53,607,yWe);_.of=function PIb(a){return Sb(this,a,(RD(),RD(),QD))};Sxb(397,9,JWe);_.of=function QJb(a){return Tb(this,a,(RD(),RD(),QD))};Sxb(824,35,jjf);_.Wc=function Otd(){return false};_.Zc=function Ptd(){return !this.U&&(this.U=Fd(this)),tN(tN(this.U,6),227)};_.Lc=function Qtd(){return !this.U&&(this.U=Fd(this)),tN(tN(this.U,6),227)};_.Nc=function Rtd(){xN(this._c(),55)&&tN(this._c(),55).of(this)};_.Pc=function Std(a){pe(this,a);if(a.Pj($Ye)){this.Sm();(!this.U&&(this.U=Fd(this)),tN(tN(this.U,6),227)).c&&((!this.U&&(this.U=Fd(this)),tN(tN(this.U,6),227)).v==null||Gie('',(!this.U&&(this.U=Fd(this)),tN(tN(this.U,6),227)).v))&&this.Tm((!this.U&&(this.U=Fd(this)),tN(tN(this.U,6),227)).a)}if(a.Pj(UYe)||a.Pj(V5e)||a.Pj(fjf)){this.Tm(Mtd(this));(!this.U&&(this.U=Fd(this)),tN(tN(this.U,6),227)).c&&((!this.U&&(this.U=Fd(this)),tN(tN(this.U,6),227)).v==null||!(!this.U&&(this.U=Fd(this)),tN(tN(this.U,6),227)).v.length)&&!(!this.U&&(this.U=Fd(this)),tN(tN(this.U,6),227)).J.length?db(this._c(),Cjf):lb(this._c(),Cjf)}};var dhb=Mge(kjf,'AbstractColorPickerConnector',824);Sxb(227,6,{6:1,41:1,227:1,3:1},n2d);_.a=null;_.b=false;_.c=false;var fob=Mge($ef,'ColorPickerState',227);eQe(Cq)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
