<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, maximum-scale=1.0" />
<title>MyCollab - Home</title>
<?php include "template/header.php"; ?>
</head>
<body>
	<div id="container">
		<?php include "template/menu.php"; ?>
		<div id="banner" class="hidden"></div>
		<div id="content" style="padding-top: 45px">
			<div id="content-left"></div>
			<div id="content-mid">
				<div class="home-mid">
					<a class="btn-signin" href="http://app.mycollab.com">Sign In</a>
					<div class="home-mid-content">
						<div class="home-mid-content-left"
							style="width: 512px; display: inline-block;">
							<ul class="price-plan-title" style="margin: 10px 0 0 25px">
								<li style="color: #1c7adb; font-size: 30px; line-height: 150%">People
									make you successful.</li>
								<li style="color: #797979; font-size: 15px; line-height: 150%">Gain
									more customers by building a relationship network that<br />
									cares about you.
								</li>
							</ul>

							<ul class="price-plan">
								<li>Create more business through automatic follow up reminders.
									Catch the opportunity before it slips through the cracks.</li>
								<li class="last">Instantly get access to your full interaction
									history, for yourself or your team. Pick up any conversation
									where you last left it off.</li>
							</ul>
							<ul class="price-plan">
								<li>Seamlessly sync all of your contacts with Salesforce,
									MailChimp, Highrise, and others. Stop wasting time on tedious
									data entry.</li>
								<li class="last">Effortlessly generate your actual address book
									from email, Facebook, LinkedIn, &amp; Twitter and keep it
									automatically up to date. Spend time with your contacts instead
									of searching for their data.</li>
							</ul>
						</div>
						<div class="price-plan-photo">
							<img src="images/image_home.png" /> <a class="gb_button"
								href="tour.php">Take a Tour</a> or <a class="gp_button"
								href="pricing.php">Try MyCollab For Free</a>

							<p
								style="padding-top: 20px; text-align: center; font-size: 15px; color: #626363">
								30-day <em>free</em> trial on all plans. No credit card
								required.
							</p>
						</div>
					</div>
				</div>
			</div>
			<div id="content-right"></div>
		</div>
		<div class="highlights">
			<dl>
				<dt>Track Everything</dt>
				<dd>The backbone of Unfuddle is our flexible, time-honored bug and
					issue tracking system. It is intuitive, powerful, and easy to
					customize.</dd>
				<dd>
					<a href="/tour">Learn More</a>
				</dd>
			</dl>
			<dl>
				<dt>Manage Source Code</dt>
				<dd>Share your Git or Subversion repositories with your entire team.
					With Unfuddle, Your code is safe and secure and accessible from
					anywhere.</dd>
				<dd>
					<a href="/tour">Learn More</a>
				</dd>
			</dl>
			<dl>
				<dt>Work Together</dt>
				<dd>Communicate with your team online through messages, wikis, and
					comments. Assign tickets, track project activity, and more.</dd>
				<dd>
					<a href="/tour">Learn More</a>
				</dd>

			</dl>
		</div>
		<?php include "template/footer.php"; ?>
	</div>
</body>
</html>
