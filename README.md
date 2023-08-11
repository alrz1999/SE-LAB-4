# SE-LAB-4

## Team members
* Alireza Ziaee
* Abdollah Zohrabi

## پرسش‌ها
1. هر یک از مفاهیم زیر را در حد یک خط توضیح دهید.
    - کد تمیز:«کد تمیز» یا`Clean Code` به کدهایی گفته می‌شود که درک و فهم آن‌ها آسان است، به راحتی می‌توان آن‌ها را ویرایش کرد و نگهداری از آن‌ها را هم می‌توان به سادگی انجام داد. کد تمیز یا همان کد نویسی تمیز نتیجه حاصل شده از ارزش‌ها، اصول و روش‌هایی در توسعه نرم افزار است 
    - بدهی فنی: بدهی فنی یا (`Technical debt`) در مهندسی نرم افزار به وجود آمدن یا تجمع کد ناکارآمد و ضعیف در یک سیستم نرم افزاری اطلاق می شود. این وضعیت بیانگر آن است که در حین توسعه یک سیستم نرم افزاری، تصمیماتی اتخاذ شده است که عملکرد یا طراحی سیستم را تغییر می دهد و در نتیجه کد قدیمی بدون تغییر یا بهبود مجدد باقی می ماند.
    - بوی بد:بوی بد در مهندسی نرم افزار به معنی وجود نقاط ضعف یا مشکلات غیر قابل قبول در یک نرم افزار است. به عبارت دیگر، بوی بد نشان دهنده وجود عیب یا قسمتی از کد نرم افزار است که نیاز به توسعه، بازنگری و بهبود دارد.
 
. طبق دسته‌بندی وب‌سایت [refactoring.guru](https://refactoring.guru/refactoring/smells)، بوهای بد کد به پنج دسته تقسیم می‌شوند. در مورد هر کدام از این پنج دسته توضیح مختصری دهید.

1.Bloaters:

کدها، روش‌ها و کلاس‌هایی هستند که به اندازه‌ای بزرگ افزایش یافته‌اند که کار کردن با آنها سخت است. معمولاً این بوها فوراً ظاهر نمی شوند، بلکه در طول زمان و با تکامل برنامه جمع می شوند.معمولا در برنامه هایی با خطوط زیاد ودرهم ریخته رخ میدهد

 
2.Object-Orientation Abusers:

این بوها کاربرد ناقص یا نادرست اصول برنامه نویسی شی گرا هستند از اصول وراثت گرفته تا  نحوه تعریف کلاس ومتد های ان.

3.Change Preventers:

این بوها به این معنی است که اگر نیاز به تغییر چیزی در یک قسمت از کد را داشته باشیم ، باید در جاهای دیگر نیز تغییرات زیادی ایجاد کنید. در نتیجه توسعه برنامه بسیار پیچیده تر و گران تر می شود

4.Dispensables:میشود `Comments`,`Lazy Class`در واقع شامل حذف کلاس ها و متغیرها و متد های اضافی و بی کار امد از کد میشوود .و شامل تکنیک هایی چون 





5.Couplers:

کوپلرها به سادگی بوهای کد هستند که نشان دهنده جفت شدن زیاد بین کلاس ها یا کل ماژول ها هستند.


3. یکی از انواع بوهای بد، Lazy Class است.
4. 
این بوی بد در کدام یک از دسته‌بندی‌های پنج‌گانه قرار می‌گیرد؟
 - 
در دسته ی `Dispensables` قرار میگیرد.

برای برطرف‌کردن این بو، استفاده از کدام بازآرایی‌ها پیشنهاد می‌شود؟
 - 
از دو متد `Inline Class` و  `Collapse Hierarchy` میتوان استفاده کرد.
   
در چه مواقعی باید این بو را نادیده گرفت؟
 -
زمانی که به منظور ترسیم اهداف توسعه برنامه در آینده ایجاد می شود. ولی باید حواسمان باشد و تعادلی بین وضوح برنامه و توسعه ان در اینده ایجاد کنیم

## گزارش کار و تصاویر مربوط به ان:
