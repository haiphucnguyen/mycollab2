$wnd.com_esofthead_mycollab_widgetset_MyCollabMobileWidgetSet.runAsyncCallback25("function QKc(){}\nfunction SKc(){}\nfunction UKc(){}\nfunction osd(){__b.call(this)}\nfunction a3b(a,b){return ML(a.G.Eo(b))}\nfunction O$d(){D_b.call(this);this.I=tQe;this.a=new Ice}\nfunction tWc(c,a){var b=c;a.notifyChildrenOfSizeChange=$ie(function(){b._k()})}\nfunction BWc(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction qWc(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction pWc(a,b){var c,d;for(c=q8d(new r8d(a.f));c.a.Ug();){d=ML(x8d(c));if(TL(a.f.Eo(d))===TL(b)){return d}}return null}\nfunction uWc(a,b){var c,d;d=pWc(a,b);d!=null&&a.f.Ho(d);c=KL(a.a.Eo(b),531);if(c){a.a.Ho(b);return ed(a,c)}else if(b){return ed(a,b)}return false}\nfunction rWc(a){var b,c,d;d=(Uvb(),a._b).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];Svb.Ag(c,$oe)}}\nfunction vWc(a,b){var c,d,e;if((bu(),b).hasAttribute(CKe)){e=gu(b,CKe);a.e.Go(e,b);Mt(b,'')}else{d=(Uvb(),ayb(b));for(c=0;c<d;c++){vWc(a,_xb(b,c))}}}\nfunction wWc(a,b,c){var d,e;if(!b){return}d=LL(a.e.Eo(c));if(!d&&a.d){throw new J4d('No location '+c+' found')}e=KL(a.f.Eo(c),9);if(e==b){return}!!e&&uWc(a,e);a.d||(d=(Uvb(),a._b));Wc(a,b,(Uvb(),d));a.f.Go(c,b)}\nfunction xWc(a,b){var c,d,e;d=b.Pd();if(d.$b!=a){return}e=KL(a.a.Eo(d),531);if(rbc(b.Nd())){if(!e){c=pWc(a,d);ed(a,d);e=new zbc(b,a.b);Vc(a,e,LL(a.e.Eo(c)));a.a.Go(d,e)}mbc(e.a)}else{if(e){c=pWc(a,d);ed(a,e);Vc(a,d,LL(a.e.Eo(c)));a.a.Ho(d)}}}\nfunction MKc(c){var d={setter:function(a,b){a.a=b},getter:function(a){return a.a}};c.tk(hnb,pQe,d);var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.tk(hnb,qQe,d);var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.tk(hnb,rQe,d)}\nfunction yWc(){var a;fd.call(this);this.e=new Ice;this.f=new Ice;this.a=new Ice;ob(this,(Uvb(),Sv($doc)));a=this._b.style;Ox(a,xye,(Ux(),kje));Ox(a,NAe,(wB(),Qke));Ox(a,_Ae,Qke);(B5b(),!A5b&&(A5b=new S5b),B5b(),A5b).a.g&&Ox(a,Bje,(BA(),dke));Kt(this._b,tQe);Kb(this._b,mJe,true)}\nfunction nsd(a){var b,c,d;if(a.a){return}c=(!a.L&&(a.L=ug(a)),KL(KL(KL(a.L,6),114),390)).c;b=(!a.L&&(a.L=ug(a)),KL(KL(KL(a.L,6),114),390)).b;c!=null&&(b=a3b(a.D,'layouts/'+c+'.html'));if(b!=null){sWc(KL(ah(a),249),b,b3b(a.D))}else{d=c!=null?'Layout file layouts/'+c+'.html is missing.':'Layout file not specified.';Mt(eb(KL(ah(a),249)),'<em>'+d+' Components will be drawn for debug purposes.<\\/em>')}a.a=true}\nfunction sWc(a,b,c){var d;b=oWc(a,b);d=wdc(c+'/layouts/');b=Q5d(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',sQe+d+'$3\"');b=Q5d(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',sQe+d+'$3\"');b=Q5d(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');Mt((Uvb(),a._b),b);a.e.Ic();vWc(a,a._b);rWc(a);a.c=_vb(a._b);!a.c&&(a.c=a._b);tWc(a,a.c);a.d=true}\nfunction oWc(a,b){var c,d,e,f,g,h,j,k;b=Q5d(b,'_UID_',a.g+'__');a.i='';d=0;f=b.toLowerCase();h='';j=f.indexOf('<script',0);while(j>0){h+=b.substr(d,j-d);j=f.indexOf('>',j);e=f.indexOf('<\\/script>',j);a.i+=b.substr(j+1,e-(j+1))+';';g=d=e+9;j=f.indexOf('<script',g)}h+=_5d(b,d,b.length-d);f=h.toLowerCase();k=f.indexOf('<body');if(k<0){h=h}else{k=f.indexOf('>',k)+1;c=f.indexOf('<\\/body>',k);c>k?(h=h.substr(k,c-k)):(h=_5d(h,k,h.length-k))}return h}\nvar pQe='childLocations',qQe='templateName',rQe='templateContents',sQe='<$1 $2src=\"',tQe='v-customlayout';dsb(1817,1,jAe);_.Ie=function PKc(){iNc(this.b,hnb,ymb);$Mc(this.b,FEe,sgb);aNc(this.b,ubb,GEe,new QKc);aNc(this.b,hnb,GEe,new SKc);aNc(this.b,sgb,GEe,new UKc);gNc(this.b,sgb,qke,new SMc(hnb));gNc(this.b,sgb,Bke,new SMc(ubb));MKc(this.b);eNc(this.b,hnb,pQe,new TMc(nGe,DL(zL(R9,1),HEe,4,0,[new SMc(amb),new SMc(Bpb)])));eNc(this.b,hnb,qQe,new SMc(Bpb));eNc(this.b,hnb,rQe,new SMc(Bpb));Nsc((!Gsc&&(Gsc=new Tsc),Gsc),this.a.d)};dsb(1819,1,_He,QKc);_.nk=function RKc(a,b){return new yWc};var T8=Y3d(OCe,'ConnectorBundleLoaderImpl/25/1/1',1819);dsb(1820,1,_He,SKc);_.nk=function TKc(a,b){return new O$d};var U8=Y3d(OCe,'ConnectorBundleLoaderImpl/25/1/2',1820);dsb(1821,1,_He,UKc);_.nk=function VKc(a,b){return new osd};var V8=Y3d(OCe,'ConnectorBundleLoaderImpl/25/1/3',1821);dsb(249,206,{14:1,11:1,13:1,12:1,25:1,30:1,15:1,28:1,10:1,9:1,249:1,19:1},yWc);_.Hc=function zWc(a){throw new D6d};_.Ic=function AWc(){Qc(this);this.f.Ic();this.a.Ic()};_._k=function CWc(){};_.sc=function DWc(a){Xb(this,a);Uvb();if(Oxb((bu(),a).type)==$oe){cbc(this,true);Nxb(a,true)}};_.tc=function EWc(){Yb(this);!!this.c&&(this.c.notifyChildrenOfSizeChange=null,undefined)};_.Jc=function FWc(a){return uWc(this,a)};_.jc=function GWc(a){Kt((Uvb(),this._b),a);Kb(this._b,mJe,true)};_.d=false;_.i='';var ubb=Y3d(ske,'VCustomLayout',249);dsb(1818,510,{7:1,16:1,122:1,92:1,133:1,26:1,35:1,34:1,32:1,153:1,254:1,31:1,3:1},osd);_.Nd=function psd(){return !this.L&&(this.L=ug(this)),KL(KL(KL(this.L,6),114),390)};_.zd=function qsd(){return !this.L&&(this.L=ug(this)),KL(KL(KL(this.L,6),114),390)};_.Ai=function rsd(){return !this.L&&(this.L=ug(this)),KL(KL(KL(this.L,6),114),390)};_.Pd=function ssd(){return KL(ah(this),249)};_.Bd=function tsd(){KL(ah(this),249).b=this.D;KL(ah(this),249).g=this.G};_.Hi=function usd(){qWc((KL(ah(this),249),_vb(eb(KL(ah(this),249)))))};_.ke=function vsd(b){var c,d,e,f,g,h;nsd(this);for(d=ai(this).Pc();d.Ug();){c=KL(d.Vg(),16);e=ML((!this.L&&(this.L=ug(this)),KL(KL(KL(this.L,6),114),390)).a.Eo(c));try{wWc(KL(ah(this),249),c.Pd(),e)}catch(a){a=asb(a);if(OL(a,37)){tie(vie((S3d(sgb),sgb.k)),\"Child not rendered as no slot with id '\"+e+\"' has been defined\")}else throw _rb(a)}}for(g=b.a.Pc();g.Ug();){f=KL(g.Vg(),16);if(f.xd()==this){continue}h=f.Pd();h.qc()&&uWc(KL(ah(this),249),h)}};_.Dd=function wsd(a){dh(this,a);nsd(this);BWc(KL(ah(this),249).i);KL(ah(this),249).i=null};_.le=function xsd(a){xWc(KL(ah(this),249),a)};_.li=function ysd(a,b){};_.a=false;var sgb=Y3d('com.vaadin.client.ui.customlayout',AIe,1818);dsb(390,114,{6:1,42:1,114:1,390:1,3:1},O$d);var hnb=Y3d('com.vaadin.shared.ui.customlayout','CustomLayoutState',390);$ie(Yq)(25);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabMobileWidgetSet-25.js\n")
