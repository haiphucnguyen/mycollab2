$wnd.com_esofthead_mycollab_widgetset_MyCollabMobileWidgetSet.runAsyncCallback21("function Kyc(){}\nfunction Myc(){}\nfunction Oyc(){}\nfunction Vfd(){TYb.call(this)}\nfunction T_b(a,b){return TN(a.H.Km(b),2)}\nfunction U_b(a){return a.e.q+'themes/'+a.Q.b}\nfunction Cwd(){vYb.call(this);this.I=K5d;this.b=new nHd}\nfunction qKc(c,a){var b=c;a.notifyChildrenOfSizeChange=TKd(function(){b.Ok()})}\nfunction yKc(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction nKc(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction mKc(a,b){var c,d;for(c=kEd(GCd(a.g));c.b.mh();){d=TN(pEd(c),2);if(ZN(a.g.Km(d))===ZN(b)){return d}}return null}\nfunction rKc(a,b){var c,d;d=mKc(a,b);d!=null&&a.g.Nm(d);c=TN(a.b.Km(b),251);if(c){a.b.Nm(b);return Yc(a,c)}else if(b){return Yc(a,b)}return false}\nfunction oKc(a){var b,c,d;d=(mtb(),a.ac).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];ktb.Pg(c,QPd)}}\nfunction sKc(a,b){var c,d,e;if((hu(),b).hasAttribute(h0d)){e=nu(b,h0d);a.f.Mm(e,b);Ut(b,'')}else{d=(mtb(),zvb(b));for(c=0;c<d;c++){sKc(a,yvb(b,c))}}}\nfunction tKc(a,b,c){var d,e;if(!b){return}d=UN(a.f.Km(c));if(!d&&a.e){throw new xAd('No location '+c+WTd)}e=TN(a.g.Km(c),18);if(e==b){return}!!e&&rKc(a,e);a.e||(d=(mtb(),a.ac));Oc(a,b,(mtb(),d));a.g.Mm(c,b)}\nfunction uKc(a,b){var c,d,e;d=b.ad();e=TN(a.b.Km(d),251);if(T8b(b.Od())){if(!e){c=mKc(a,d);Yc(a,d);e=new Z8b(b,a.c);Nc(a,e,UN(a.f.Km(c)));a.b.Mm(d,e)}O8b(e.b)}else{if(e){c=mKc(a,d);Yc(a,e);Nc(a,d,UN(a.f.Km(c)));a.b.Nm(d)}}}\nfunction Gyc(c){var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.hk(Vjb,G5d,d);var d={setter:function(a,b){a.d=b},getter:function(a){return a.d}};c.hk(Vjb,H5d,d);var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.hk(Vjb,I5d,d)}\nfunction vKc(){var a;Zc.call(this);this.f=new nHd;this.g=new nHd;this.b=new nHd;this.j='';this.e=false;ib(this,(mtb(),$v($doc)));a=this.ac.style;Xx(a,$Rd,(cy(),cLd));a[TTd]=0+(OB(),tLd);a[lUd]=BMd;(n2b(),!m2b&&(m2b=new E2b),n2b(),m2b).b.i&&Xx(a,rLd,(QA(),ULd));St(this.ac,K5d);Eb(this.ac,S$d,true)}\nfunction Ufd(a){var b,c;if(TN(Qg(a),83).e){return}c=(!a.G&&(a.G=ig(a)),TN(TN(TN(a.G,5),38),164)).d;b=(!a.G&&(a.G=ig(a)),TN(TN(TN(a.G,5),38),164)).c;if(c!=null){b=T_b(a.v,'layouts/'+c+'.html');b==null&&(b='<em>Layout file layouts/'+c+'.html is missing. Components will be drawn for debug purposes.<\\/em>')}pKc(TN(Qg(a),83),b,U_b(a.v))}\nfunction lKc(a,b){var c,d,e,f,g,i,j,k;b=HBd(b,'_UID_',a.i+'__');a.j='';d=0;f=b.toLowerCase();i='';j=f.indexOf('<script',0);while(j>0){i+=MBd(b,d,j);j=f.indexOf('>',j);e=f.indexOf('<\\/script>',j);a.j+=MBd(b,j+1,e)+';';g=d=e+9;j=f.indexOf('<script',g)}i+=LBd(b,d);f=i.toLowerCase();k=f.indexOf('<body');if(k<0){i=i}else{k=f.indexOf('>',k)+1;c=f.indexOf('<\\/body>',k);c>k?(i=MBd(i,k,c)):(i=LBd(i,k))}return i}\nfunction pKc(a,b,c){var d;b=lKc(a,b);d=c+'/layouts/';b=HBd(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',J5d+d+'$3\"');b=HBd(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',J5d+d+'$3\"');b=HBd(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');Ut((mtb(),a.ac),b);a.f.Ic();sKc(a,a.ac);oKc(a);a.d=utb(a.ac);!a.d&&(a.d=a.ac);qKc(a,a.d);a.e=true}\nvar G5d='templateContents',H5d='templateName',I5d='childLocations',J5d='<$1 $2src=\"',K5d='v-customlayout';kpb(2091,1,pTd);_.Ie=function Jyc(){XBc(this.c,Vjb,kjb);NBc(this.c,aXd,igb);QBc(this.c,Vjb,fXd,new Kyc);QBc(this.c,igb,fXd,new Myc);QBc(this.c,nbb,fXd,new Oyc);VBc(this.c,igb,bMd,new FBc(Vjb));VBc(this.c,igb,lMd,new FBc(nbb));Gyc(this.c);TBc(this.c,Vjb,G5d,new FBc(Rlb));TBc(this.c,Vjb,H5d,new FBc(Rlb));TBc(this.c,Vjb,I5d,new GBc(oYd,IN(fob,uXd,8,0,[new FBc(Uib),new FBc(Rlb)])));Hkc((!Akc&&(Akc=new Nkc),Akc),this.b.e)};kpb(1966,1,NZd,Kyc);_.bk=function Lyc(a,b){return new Cwd};kpb(1225,1,NZd,Myc);_.bk=function Nyc(a,b){return new Vfd};kpb(1086,1,NZd,Oyc);_.bk=function Pyc(a,b){return new vKc};kpb(83,oLd,{2334:1,2226:1,618:1,2530:1,118:1,2129:1,2560:1,270:1,303:1,18:1,83:1,200:1},vKc);_.Hc=function wKc(a){throw new CCd};_.Ic=function xKc(){Ic(this);this.g.Ic();this.b.Ic()};_.Ok=function zKc(){};_.tc=function AKc(a){Rb(this,a);if((mtb(),gvb(a))==QPd){j8b(this,true);fvb(a,true)}};_.uc=function BKc(){Sb(this);!!this.d&&(this.d.notifyChildrenOfSizeChange=null,undefined)};_.Jc=function CKc(a){return rKc(this,a)};_.kc=function DKc(a){St((mtb(),this.ac),a);Eb(this.ac,S$d,true)};_.e=false;kpb(1460,1983,{359:1,7:1,1346:1,75:1,302:1,67:1,1888:1,140:1,194:1,81:1,417:1,129:1,3:1},Vfd);_.Od=function Wfd(){return !this.G&&(this.G=ig(this)),TN(TN(TN(this.G,5),38),164)};_.Ad=function Xfd(){return !this.G&&(this.G=ig(this)),TN(TN(TN(this.G,5),38),164)};_.Ii=function Yfd(){return !this.G&&(this.G=ig(this)),TN(TN(TN(this.G,5),38),164)};_.ad=function Zfd(){return TN(Qg(this),83)};_.Cd=function $fd(){TN(Qg(this),83).c=this.v;TN(Qg(this),83).i=this.A};_.Pi=function _fd(){nKc((TN(Qg(this),83),utb(Y(TN(Qg(this),83)))))};_.je=function agd(b){var c,d,e,f,g,i;Ufd(this);for(d=Ph(this).Pc();d.mh();){c=TN(d.nh(),7);e=TN((!this.G&&(this.G=ig(this)),TN(TN(TN(this.G,5),38),164)).b.Km(c),2);try{tKc(TN(Qg(this),83),c.ad(),e)}catch(a){a=hpb(a);if(!VN(a,291))throw gpb(a)}}for(g=b.b.Pc();g.mh();){f=TN(g.nh(),7);if(f.yd()==this){continue}i=f.ad();i.rc()&&rKc(TN(Qg(this),83),i)}};_.Ed=function bgd(a){Sg(this,a);Ufd(this);yKc(TN(Qg(this),83).j);TN(Qg(this),83).j=null};_.ke=function cgd(a){uKc(TN(Qg(this),83),a)};_.ti=function dgd(a,b){};kpb(164,38,{5:1,349:1,38:1,164:1,3:1},Cwd);var Vjb=Xzd('com.vaadin.shared.ui.customlayout.','CustomLayoutState',164),igb=Xzd('com.vaadin.client.ui.customlayout.',e$d,1460),nbb=Xzd(_0d,'VCustomLayout',83),A8=Xzd(N1d,'ConnectorBundleLoaderImpl$21$1$1',1966),B8=Xzd(N1d,'ConnectorBundleLoaderImpl$21$1$2',1225),C8=Xzd(N1d,'ConnectorBundleLoaderImpl$21$1$3',1086);TKd(Xq)(21);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabMobileWidgetSet-21.js\n")
