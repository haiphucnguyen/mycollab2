$wnd.com_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function rIb(a){return a.g}\nfunction tIb(a,b){nHb(a,b);--a.i}\nfunction urd(){Od.call(this)}\nfunction u2d(){Se.call(this);this.H=gof}\nfunction fw(a){return (xt(),wt).Ee(a,'col')}\nfunction TAc(a,b){V9b(a.a,new YKc(new mLc(zmb),'openPopup'),BK(xK(uqb,1),DVe,1,3,[(She(),b?Rhe:Qhe)]))}\nfunction sIb(a,b){if(b<0){throw new Mhe('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new Mhe(i_e+b+j_e+a.i)}}\nfunction vIb(a,b){if(a.i==b){return}if(b<0){throw new Mhe('Cannot set number of rows to '+b)}if(a.i<b){xIb((_Ab(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){tIb(a,a.i-1)}}}\nfunction wIb(a,b){XGb();uHb.call(this);oHb(this,new QHb(this));rHb(this,new kJb(this));pHb(this,new $Ib(this));uIb(this,b);vIb(this,a)}\nfunction ZIb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){ls(a.a,fw($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){us(a.a,a.a.lastChild)}}}\nfunction xIb(a,b,c){var d=$doc.createElement('td');d.innerHTML=l2e;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction trd(a){if((!a.db&&(a.db=Tc(a)),IK(IK(a.db,6),230)).c&&((!a.db&&(a.db=Tc(a)),IK(IK(a.db,6),230)).w==null||wke('',(!a.db&&(a.db=Tc(a)),IK(IK(a.db,6),230)).w))){return (!a.db&&(a.db=Tc(a)),IK(IK(a.db,6),230)).a}return (!a.db&&(a.db=Tc(a)),IK(IK(a.db,6),230)).w}\nfunction uIb(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new Mhe('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){ZGb(a,c,d);e=_Gb(a,c,d,false);f=gJb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){jHb(a,c,d)}}}a.g=b;ZIb(a.I,b,false)}\nvar aof='showDefaultCaption',bof='setColor',cof='setOpen',dof='background',eof={46:1,7:1,12:1,26:1,31:1,34:1,33:1,30:1,3:1},fof='com.vaadin.client.ui.colorpicker',gof='v-colorpicker',yof='v-default-caption-width';$wb(1,null,{});_.gC=function X(){return this.cZ};$wb(141,9,_$e);_.wf=function LEb(a){return Tb(this,a,(GD(),GD(),FD))};$wb(857,28,k_e);_.wf=function vHb(a){return Tb(this,a,(GD(),GD(),FD))};$wb(276,45,m_e);_.wf=function cIb(a){return Tb(this,a,(GD(),GD(),FD))};$wb(674,857,k_e,wIb);_.Lg=function yIb(){var a;a=(_Ab(),Hw($doc));ft(a,l2e);return a};_.Mg=function zIb(a){return rIb(this)};_.Ng=function AIb(){return this.i};_.Og=function BIb(a,b){sIb(this,a);if(b<0){throw new Mhe('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new Mhe(g_e+b+h_e+this.g)}};_.Pg=function CIb(a){sIb(this,a)};_.g=0;_.i=0;var RS=Gie(aVe,'Grid',674);$wb(113,168,p_e);_.wf=function IIb(a){return Tb(this,a,(GD(),GD(),FD))};$wb(400,9,A_e);_.wf=function IJb(a){return Ub(this,a,(GD(),GD(),FD))};$wb(843,34,eof);_.cd=function vrd(){return false};_.fd=function wrd(){return !this.db&&(this.db=Tc(this)),IK(IK(this.db,6),230)};_.Tc=function xrd(){return !this.db&&(this.db=Tc(this)),IK(IK(this.db,6),230)};_.Vc=function yrd(){MK(this.hd(),54)&&IK(this.hd(),54).wf(this)};_.Xc=function zrd(a){Gd(this,a);if(a.$i(_1e)){this.jm();(!this.db&&(this.db=Tc(this)),IK(IK(this.db,6),230)).c&&((!this.db&&(this.db=Tc(this)),IK(IK(this.db,6),230)).w==null||wke('',(!this.db&&(this.db=Tc(this)),IK(IK(this.db,6),230)).w))&&this.km((!this.db&&(this.db=Tc(this)),IK(IK(this.db,6),230)).a)}if(a.$i(q2e)||a.$i(P9e)||a.$i(aof)){this.km(trd(this));(!this.db&&(this.db=Tc(this)),IK(IK(this.db,6),230)).c&&((!this.db&&(this.db=Tc(this)),IK(IK(this.db,6),230)).w==null||!(!this.db&&(this.db=Tc(this)),IK(IK(this.db,6),230)).w.length)&&!(!this.db&&(this.db=Tc(this)),IK(IK(this.db,6),230)).K.length?this.hd().fc(yof):this.hd().kc(yof)}};var Seb=Gie(fof,'AbstractColorPickerConnector',843);$wb(230,6,{6:1,39:1,230:1,3:1},u2d);_.a=null;_.b=false;_.c=false;var Amb=Gie(rjf,'ColorPickerState',230);JUe(rq)(9);\n//# sourceURL=com.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
