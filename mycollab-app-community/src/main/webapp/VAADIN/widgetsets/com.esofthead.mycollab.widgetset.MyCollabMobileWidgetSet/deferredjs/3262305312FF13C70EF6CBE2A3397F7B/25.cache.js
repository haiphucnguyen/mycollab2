$wnd.com_esofthead_mycollab_widgetset_MyCollabMobileWidgetSet.runAsyncCallback25("function bIc(){}\nfunction dIc(){}\nfunction fIc(){}\nfunction Bpd(){w$b.call(this)}\nfunction x1b(a,b){return IL(a.G.no(b))}\nfunction bUd(){$Zb.call(this);this.I=fGe;this.a=new I4d}\nfunction GTc(c,a){var b=c;a.notifyChildrenOfSizeChange=u9d(function(){b.Yk()})}\nfunction OTc(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction DTc(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction CTc(a,b){var c,d;for(c=b1d(new c1d(a.f));c.a.Ug();){d=IL(h1d(c));if(PL(a.f.no(d))===PL(b)){return d}}return null}\nfunction HTc(a,b){var c,d;d=CTc(a,b);d!=null&&a.f.qo(d);c=GL(a.a.no(b),519);if(c){a.a.qo(b);return bd(a,c)}else if(b){return bd(a,b)}return false}\nfunction ETc(a){var b,c,d;d=(vub(),a._b).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];tub.Ag(c,tfe)}}\nfunction ITc(a,b){var c,d,e;if((Zt(),b).hasAttribute(yAe)){e=cu(b,yAe);a.e.po(e,b);It(b,'')}else{d=(vub(),Dwb(b));for(c=0;c<d;c++){ITc(a,Cwb(b,c))}}}\nfunction JTc(a,b,c){var d,e;if(!b){return}d=HL(a.e.no(c));if(!d&&a.d){throw new EZd('No location '+c+' found')}e=GL(a.f.no(c),9);if(e==b){return}!!e&&HTc(a,e);a.d||(d=(vub(),a._b));Tc(a,b,(vub(),d));a.f.po(c,b)}\nfunction KTc(a,b){var c,d,e;d=b.Pd();if(d.$b!=a){return}e=GL(a.a.no(d),519);if(N9b(b.Nd())){if(!e){c=CTc(a,d);bd(a,d);e=new V9b(b,a.b);Sc(a,e,HL(a.e.no(c)));a.a.po(d,e)}I9b(e.a)}else{if(e){c=CTc(a,d);bd(a,e);Sc(a,d,HL(a.e.no(c)));a.a.qo(d)}}}\nfunction ZHc(c){var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.qk(fmb,bGe,d);var d={setter:function(a,b){a.a=b},getter:function(a){return a.a}};c.qk(fmb,cGe,d);var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.qk(fmb,dGe,d)}\nfunction LTc(){var a;cd.call(this);this.e=new I4d;this.f=new I4d;this.a=new I4d;lb(this,(vub(),Ov($doc)));a=this._b.style;Kx(a,Qoe,(Qx(),G9d));Kx(a,are,(sB(),kbe));Kx(a,mre,kbe);(X3b(),!W3b&&(W3b=new m4b),X3b(),W3b).a.g&&Kx(a,X9d,(xA(),zae));Gt(this._b,fGe);Hb(this._b,jze,true)}\nfunction Apd(a){var b,c;if(a.a){return}c=(!a.F&&(a.F=rg(a)),GL(GL(GL(a.F,6),114),387)).c;b=(!a.F&&(a.F=rg(a)),GL(GL(GL(a.F,6),114),387)).b;if(c!=null){b=x1b(a.u,'layouts/'+c+'.html');b==null&&It(bb(GL(Zg(a),243)),'<em>Layout file layouts/'+c+'.html is missing. Components will be drawn for debug purposes.<\\/em>')}b!=null&&FTc(GL(Zg(a),243),b,y1b(a.u));a.a=true}\nfunction FTc(a,b,c){var d;b=BTc(a,b);d=Sbc(c+'/layouts/');b=I$d(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',eGe+d+'$3\"');b=I$d(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',eGe+d+'$3\"');b=I$d(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');It((vub(),a._b),b);a.e.Ic();ITc(a,a._b);ETc(a);a.c=Cub(a._b);!a.c&&(a.c=a._b);GTc(a,a.c);a.d=true}\nfunction BTc(a,b){var c,d,e,f,g,h,i,j;b=I$d(b,'_UID_',a.g+'__');a.i='';d=0;f=b.toLowerCase();h='';i=f.indexOf('<script',0);while(i>0){h+=b.substr(d,i-d);i=f.indexOf('>',i);e=f.indexOf('<\\/script>',i);a.i+=b.substr(i+1,e-(i+1))+';';g=d=e+9;i=f.indexOf('<script',g)}h+=S$d(b,d,b.length-d);f=h.toLowerCase();j=f.indexOf('<body');if(j<0){h=h}else{j=f.indexOf('>',j)+1;c=f.indexOf('<\\/body>',j);c>j?(h=h.substr(j,c-j)):(h=S$d(h,j,h.length-j))}return h}\nvar bGe='templateContents',cGe='childLocations',dGe='templateName',eGe='<$1 $2src=\"',fGe='v-customlayout';Gqb(1792,1,Cqe);_.Ie=function aIc(){vKc(this.b,fmb,wlb);lKc(this.b,Oue,Zfb);nKc(this.b,fmb,Pue,new bIc);nKc(this.b,_ab,Pue,new dIc);nKc(this.b,Zfb,Pue,new fIc);tKc(this.b,Zfb,Xae,new dKc(_ab));tKc(this.b,Zfb,Mae,new dKc(fmb));ZHc(this.b);rKc(this.b,fmb,bGe,new dKc(wob));rKc(this.b,fmb,cGe,new eKc(Iwe,zL(vL(w9,1),Que,4,0,[new dKc($kb),new dKc(wob)])));rKc(this.b,fmb,dGe,new dKc(wob));jqc((!cqc&&(cqc=new pqc),cqc),this.a.d)};Gqb(1794,1,$xe,bIc);_.kk=function cIc(a,b){return new bUd};var y8=UYd(Yse,'ConnectorBundleLoaderImpl/25/1/1',1794);Gqb(1795,1,$xe,dIc);_.kk=function eIc(a,b){return new LTc};var z8=UYd(Yse,'ConnectorBundleLoaderImpl/25/1/2',1795);Gqb(1796,1,$xe,fIc);_.kk=function gIc(a,b){return new Bpd};var A8=UYd(Yse,'ConnectorBundleLoaderImpl/25/1/3',1796);Gqb(243,202,{14:1,11:1,13:1,12:1,24:1,30:1,15:1,27:1,10:1,9:1,243:1,19:1},LTc);_.Hc=function MTc(a){throw new t_d};_.Ic=function NTc(){Nc(this);this.f.Ic();this.a.Ic()};_.Yk=function PTc(){};_.sc=function QTc(a){Ub(this,a);vub();if(pwb((Zt(),a).type)==tfe){y9b(this,true);owb(a,true)}};_.tc=function RTc(){Vb(this);!!this.c&&(this.c.notifyChildrenOfSizeChange=null,undefined)};_.Jc=function STc(a){return HTc(this,a)};_.jc=function TTc(a){Gt((vub(),this._b),a);Hb(this._b,jze,true)};_.d=false;_.i='';var _ab=UYd(Oae,'VCustomLayout',243);Gqb(1793,498,{7:1,16:1,120:1,91:1,132:1,26:1,34:1,33:1,31:1,150:1,249:1,32:1,3:1},Bpd);_.Nd=function Cpd(){return !this.F&&(this.F=rg(this)),GL(GL(GL(this.F,6),114),387)};_.zd=function Dpd(){return !this.F&&(this.F=rg(this)),GL(GL(GL(this.F,6),114),387)};_.Ai=function Epd(){return !this.F&&(this.F=rg(this)),GL(GL(GL(this.F,6),114),387)};_.Pd=function Fpd(){return GL(Zg(this),243)};_.Bd=function Gpd(){GL(Zg(this),243).b=this.u;GL(Zg(this),243).g=this.w};_.Hi=function Hpd(){DTc((GL(Zg(this),243),Cub(bb(GL(Zg(this),243)))))};_.ke=function Ipd(b){var c,d,e,f,g,h;Apd(this);for(d=Zh(this).Pc();d.Ug();){c=GL(d.Vg(),16);e=IL((!this.F&&(this.F=rg(this)),GL(GL(GL(this.F,6),114),387)).a.no(c));try{JTc(GL(Zg(this),243),c.Pd(),e)}catch(a){a=Dqb(a);if(KL(a,37)){P8d(R8d((OYd(Zfb),Zfb.k)),\"Child not rendered as no slot with id '\"+e+\"' has been defined\")}else throw Cqb(a)}}for(g=b.a.Pc();g.Ug();){f=GL(g.Vg(),16);if(f.xd()==this){continue}h=f.Pd();h.qc()&&HTc(GL(Zg(this),243),h)}};_.Dd=function Jpd(a){_g(this,a);Apd(this);OTc(GL(Zg(this),243).i);GL(Zg(this),243).i=null};_.le=function Kpd(a){KTc(GL(Zg(this),243),a)};_.li=function Lpd(a,b){};_.a=false;var Zfb=UYd('com.vaadin.client.ui.customlayout',zye,1793);Gqb(387,114,{6:1,41:1,114:1,387:1,3:1},bUd);var fmb=UYd('com.vaadin.shared.ui.customlayout','CustomLayoutState',387);u9d(Vq)(25);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabMobileWidgetSet-25.js\n")
