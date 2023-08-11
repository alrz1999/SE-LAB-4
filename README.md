# SE-LAB-4

## Team members
* Alireza Ziaee
* Abdollah Zohrabi

<div dir='rtl'>

## پرسش‌ها

1. هر یک از مفاهیم زیر را در حد یک خط توضیح دهید.
    - کد تمیز
        - «کد تمیز» یا`Clean Code` به کدهایی گفته می‌شود که درک و فهم آن‌ها آسان است، به راحتی می‌توان آن‌ها را ویرایش کرد و نگهداری از آن‌ها را هم می‌توان به سادگی انجام داد. کد تمیز یا همان کدنویسی تمیز نتیجه حاصل شده از ارزش‌ها، اصول و روش‌هایی در توسعه نرم‌افزار است 
    - بدهی فنی
        - بدهی فنی یا (`Technical debt`) در مهندسی نرم افزار به وجود آمدن یا تجمع کد ناکارآمد و ضعیف در یک سیستم نرم افزاری اطلاق می شود. این وضعیت بیانگر آن است که در حین توسعه یک سیستم نرم افزاری، تصمیماتی اتخاذ شده است که عملکرد یا طراحی سیستم را تغییر می دهد و در نتیجه کد قدیمی بدون تغییر یا بهبود مجدد باقی می ماند
    - بوی بد
        - بوی بد در مهندسی نرم افزار به معنی وجود نقاط ضعف یا مشکلات غیر قابل قبول در یک نرم افزار است. به عبارت دیگر، بوی بد نشان دهنده وجود عیب یا قسمتی از کد نرم افزار است که نیاز به توسعه، بازنگری و بهبود دارد
2. طبق دسته‌بندی وب‌سایت [refactoring.guru](https://refactoring.guru/refactoring/smells)، بوهای بد کد به پنج دسته تقسیم می‌شوند. در مورد هر کدام از این پنج دسته توضیح مختصری دهید.
    - یک Bloaters
        - کدها، روش‌ها و کلاس‌هایی هستند که به اندازه‌ای بزرگ افزایش یافته‌اند که کار کردن با آنها سخت است. معمولاً این بوها فوراً ظاهر نمی شوند، بلکه در طول زمان و با تکامل برنامه جمع می شوند.معمولا در برنامه هایی با خطوط زیاد ودرهم ریخته رخ می‌دهد
    - دو Object-Orientation Abusers
        - این بوها کاربرد ناقص یا نادرست اصول برنامه نویسی شی گرا هستند از اصول وراثت گرفته تا  نحوه تعریف کلاس ومتد های ان
    - سه Change Preventers
        - این بوها به این معنی است که اگر نیاز به تغییر چیزی در یک قسمت از کد را داشته باشیم ، باید در جاهای دیگر نیز تغییرات زیادی ایجاد کنید. در نتیجه توسعه برنامه بسیار پیچیده تر و گران تر می شود
    - چهار Dispensable
        - میشود `Comments`,`Lazy Class`در واقع شامل حذف کلاس ها و متغیرها و متد های اضافی و بی کار امد از کد میشوود .و شامل تکنیک هایی چون
    - پنج Couplers
        - کوپلرها به سادگی بوهای کد هستند که نشان دهنده جفت شدن زیاد بین کلاس ها یا کل ماژول ها هستند
4. یکی از انواع بوهای بد، Lazy Class است.
    - این بوی بد در کدام یک از دسته‌بندی‌های پنج‌گانه قرار می‌گیرد؟
        - در دسته ی `Dispensables` قرار میگیرد.
    - برای برطرف‌کردن این بو، استفاده از کدام بازآرایی‌ها پیشنهاد می‌شود؟
        - از دو متد `Inline Class` و  `Collapse Hierarchy` میتوان استفاده کرد.
    - در چه مواقعی باید این بو را نادیده گرفت؟
        - زمانی که به منظور ترسیم اهداف توسعه برنامه در آینده ایجاد می شود. ولی باید حواسمان باشد و تعادلی بین وضوح برنامه و توسعه ان در اینده ایجاد کنیم
</div>

## گزارش کار و تصاویر مربوط به ان:

در کامیت‌های زیر بعد از اضافه کردن پروژه، برخی `refactor‍` ها و `reformat‍` های اولیه جهت خواناتر شدن پروژه انجام گرفته‌است.
1. در کامیت‌های زیر، کدهای بدون استفاده حذف شده‌اند؛ TODO ها حذف شده‌اند؛ نام پکیج‌ها، کلاس‌ها و فیلدها اصلاح شده‌است؛ برخی غلط‌های املایی اصلاح شده‌اند:
    - کامیت [Remove Unused codes and TODOs](https://github.com/alrz1999/SE-LAB-4/commit/076fcbf60768b9bd77032600f38b5213305271a9)
    - کامیت [Fix Package names](https://github.com/alrz1999/SE-LAB-4/commit/68fe985d05f0fcd376e95f42a5a32441c8821e8e)
    - کامیت [Fix typo](https://github.com/alrz1999/SE-LAB-4/commit/e1918381afd02b0643ba8638d2818de9fe1d6433)
    - کامیت [Fix Class names](https://github.com/alrz1999/SE-LAB-4/commit/40c89b72d1372c1c63b9ddc332719c9cebfaa637)
    - کامیت [Fix fields names](https://github.com/alrz1999/SE-LAB-4/commit/e28f6f80c5691bab1985635fbdca9189b444f9e0)
    - کامیت [Remove unused codes](https://github.com/alrz1999/SE-LAB-4/commit/1a1f57e76774b8e4c409e033bc37266c1adfb539)
    - کامیت [Fix enums naming](https://github.com/alrz1999/SE-LAB-4/commit/804b4cd5c3a7309e5f30fd7b7ba8d8207bb1697e)
2. در کامیت‌های زیر بازآرایی‌های خواسته‌شده انجام‌شده‌اند.
    - کامیت [Replace Conditional with Polymorphism for Symbol VarType](https://github.com/alrz1999/SE-LAB-4/commit/9955c5398ce7b5b749a384abc71d80f03a0c1d4b)
    - کامیت [Separate Query from Modifier in SymbolTable](https://github.com/alrz1999/SE-LAB-4/commit/8cf452981ad8b06e9fb199776b6aabdea0b373fa)
    - کامیت [Self Encapsulate Field for Address class](https://github.com/alrz1999/SE-LAB-4/commit/f6257af6a9583cc4c8df9e5df56847221fd57825)
    - کامیت [Replace Magic Number with Symbolic Constant](https://github.com/alrz1999/SE-LAB-4/commit/857cf92e75aff2c8806b2dc0afb6033097fd9496)
    - کامیت [Self Encapsulate Field for _3AddressCode class](https://github.com/alrz1999/SE-LAB-4/commit/167578325aa0a54eff2a33a6ddfaf74bd81f7393)
    - کامیت [Self Encapsulate Field for Action class](https://github.com/alrz1999/SE-LAB-4/commit/c80aa7e963a027f29da93054c97d1e8ea6924a30)
    - کامیت [Temporary Field in Parser class](https://github.com/alrz1999/SE-LAB-4/commit/a50bd86e4b9db3eca2c81c7122dba1755f8b856c)
    - کامیت [Replace Type Code with Subclasses](https://github.com/alrz1999/SE-LAB-4/commit/46ae38fa73466f2c8e3184c19cc3215b08e59900)
    - کامیت [Self Encapsulate Field for SymbolTable class](https://github.com/alrz1999/SE-LAB-4/commit/edd08a620629824beb4c881faaec47188c0cea5b)
    - کامیت [Self Encapsulate Field for Method class](https://github.com/alrz1999/SE-LAB-4/commit/d8a15ccb3caf28cb3dd15af0ff7eec348cf84ff5)
    - کامیت [Self Encapsulate Field for Klass class](https://github.com/alrz1999/SE-LAB-4/commit/de01a08a03aa0deb88a13cf63a8f179573ee46f1)
    - کامیت [Replace Conditional with Polymorphism for Address class](https://github.com/alrz1999/SE-LAB-4/commit/41739d8422eb6a61c847977f53c60c265a96b049)
    - کامیت [Extract Method in SymbolTable](https://github.com/alrz1999/SE-LAB-4/commit/f8f8a04c090faee099770a8e3072daa74c111ada)
    - کامیت [codegenerator Facade](https://github.com/alrz1999/SE-LAB-4/commit/7e4ec2f63ab1ade860200f4e7204890a268787ee)
    - کامیت [parser Facade](https://github.com/alrz1999/SE-LAB-4/commit/2895923b08e0c5811fbefc3cf311ae6170427c01)
    - کامیت [Add Interfaces](https://github.com/alrz1999/SE-LAB-4/commit/7c8165d89027a385e76167cce4375b8fa2a1594b)
