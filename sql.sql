USE [master]
GO
/****** Object:  Database [JobCV]    Script Date: 8/8/2023 1:55:12 AM ******/
CREATE DATABASE [JobCV]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'JobCV', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\JobCV.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'JobCV_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\JobCV_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [JobCV] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [JobCV].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [JobCV] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [JobCV] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [JobCV] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [JobCV] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [JobCV] SET ARITHABORT OFF 
GO
ALTER DATABASE [JobCV] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [JobCV] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [JobCV] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [JobCV] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [JobCV] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [JobCV] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [JobCV] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [JobCV] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [JobCV] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [JobCV] SET  DISABLE_BROKER 
GO
ALTER DATABASE [JobCV] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [JobCV] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [JobCV] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [JobCV] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [JobCV] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [JobCV] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [JobCV] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [JobCV] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [JobCV] SET  MULTI_USER 
GO
ALTER DATABASE [JobCV] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [JobCV] SET DB_CHAINING OFF 
GO
ALTER DATABASE [JobCV] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [JobCV] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [JobCV] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [JobCV] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [JobCV] SET QUERY_STORE = ON
GO
ALTER DATABASE [JobCV] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [JobCV]
GO
/****** Object:  Table [dbo].[apply_post]    Script Date: 8/8/2023 1:55:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[apply_post](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[create_at] [varchar](255) NULL,
	[name_cv] [varchar](255) NULL,
	[status] [int] NOT NULL,
	[text] [varchar](255) NULL,
	[recruitment_id] [varchar](255) NULL,
	[user_id] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 8/8/2023 1:55:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id_category] [int] IDENTITY(1,1) NOT NULL,
	[category_name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_category] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[companies]    Script Date: 8/8/2023 1:55:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[companies](
	[id_company] [varchar](255) NOT NULL,
	[address] [nvarchar](max) NULL,
	[company_name] [nvarchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[email] [varchar](255) NULL,
	[logo] [varchar](255) NULL,
	[phone_number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_company] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[curriculum_vitae]    Script Date: 8/8/2023 1:55:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[curriculum_vitae](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[file_name] [varchar](255) NULL,
	[user_id] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[follow_company]    Script Date: 8/8/2023 1:55:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[follow_company](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[company_id] [varchar](255) NULL,
	[user_id] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[recruitment]    Script Date: 8/8/2023 1:55:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[recruitment](
	[id_recruitment] [varchar](255) NOT NULL,
	[address] [nvarchar](max) NULL,
	[create_at] [varchar](255) NULL,
	[deadline] [varchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[experience] [nvarchar](max) NULL,
	[quantity] [int] NOT NULL,
	[rank] [nvarchar](255) NULL,
	[salary] [float] NOT NULL,
	[status] [int] NOT NULL,
	[title] [nvarchar](255) NULL,
	[type] [nvarchar](255) NULL,
	[views] [int] NOT NULL,
	[id_category] [int] NULL,
	[company_id] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_recruitment] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 8/8/2023 1:55:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id_role] [int] IDENTITY(1,1) NOT NULL,
	[role_name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_role] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[save_job]    Script Date: 8/8/2023 1:55:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[save_job](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[recruitment_id] [varchar](255) NULL,
	[user_id] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 8/8/2023 1:55:13 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id_user] [varchar](255) NOT NULL,
	[address] [nvarchar](max) NULL,
	[description] [nvarchar](max) NULL,
	[email] [varchar](255) NULL,
	[enable] [bit] NOT NULL,
	[full_name] [nvarchar](255) NULL,
	[image] [varchar](255) NULL,
	[password] [varchar](128) NULL,
	[phone_number] [varchar](255) NULL,
	[id_company] [varchar](255) NULL,
	[role_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_user] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[apply_post] ON 

INSERT [dbo].[apply_post] ([id], [create_at], [name_cv], [status], [text], [recruitment_id], [user_id]) VALUES (1, N'2023-08-08', N't@gmail.com_35_18CV_NguyenPhucThinh.pdf', 1, N'
                      Gi?i thi?u   ', N'62205186-389d-4caa-b477-3799ac729bff', N'4ae7a43b-1bbb-44b3-adc2-36560df2e5fb')
SET IDENTITY_INSERT [dbo].[apply_post] OFF
GO
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([id_category], [category_name]) VALUES (1, N'JAVA')
INSERT [dbo].[category] ([id_category], [category_name]) VALUES (2, N'NODE JS')
INSERT [dbo].[category] ([id_category], [category_name]) VALUES (3, N'PHP')
INSERT [dbo].[category] ([id_category], [category_name]) VALUES (4, N'FRONT END')
INSERT [dbo].[category] ([id_category], [category_name]) VALUES (5, N'MOBILE')
INSERT [dbo].[category] ([id_category], [category_name]) VALUES (6, N'AI')
INSERT [dbo].[category] ([id_category], [category_name]) VALUES (7, N'C,C++')
INSERT [dbo].[category] ([id_category], [category_name]) VALUES (8, N'C#')
INSERT [dbo].[category] ([id_category], [category_name]) VALUES (9, N'PYTHON')
INSERT [dbo].[category] ([id_category], [category_name]) VALUES (10, N'RUBY')
SET IDENTITY_INSERT [dbo].[category] OFF
GO
INSERT [dbo].[companies] ([id_company], [address], [company_name], [description], [email], [logo], [phone_number]) VALUES (N'ba09b658-0164-416c-9cb6-c6383ca0512a', N'Nguyễn Thái Sơn', N'FPT Soft', N'', N'nguyenthinhc9@gmail.com', NULL, N'1231231323')
GO
SET IDENTITY_INSERT [dbo].[curriculum_vitae] ON 

INSERT [dbo].[curriculum_vitae] ([id], [file_name], [user_id]) VALUES (1, N't@gmail.com_35_18CV_NguyenPhucThinh.pdf', N'4ae7a43b-1bbb-44b3-adc2-36560df2e5fb')
SET IDENTITY_INSERT [dbo].[curriculum_vitae] OFF
GO
INSERT [dbo].[recruitment] ([id_recruitment], [address], [create_at], [deadline], [description], [experience], [quantity], [rank], [salary], [status], [title], [type], [views], [id_category], [company_id]) VALUES (N'62205186-389d-4caa-b477-3799ac729bff', N'FPT Software Đường D1 Phường Tân Phú , District 9, Ho Chi Minh', N'2023-08-08', N'2023-09-09', N'<h2><strong>3 Lý do để gia nhập công ty</strong></h2><ul><li>Global Exposure</li><li>Fast Track Career</li><li>Diversified Jobs &amp; Technologies</li></ul><h2><strong>Mô tả công việc</strong></h2><ul><li>&nbsp;Work with Client’s designers and product owners to deliver production-ready source code based upon assigned features and requirements.</li><li>Test and remediate issues found during development in accordance with accepted quality control practices.</li><li>&nbsp;When needed or requested, provide technical expertise and insight related to engineering and design efforts.</li><li>&nbsp;Provide ongoing continuous delivery enhancements as defined by the Client delivery team.&nbsp;</li><li>&nbsp;Follow engineering and user experience patterns prescribed by the Client delivery team</li><li>&nbsp;Lead technical engagement &amp; collaborate with project teams on business requirement, analysis, design, development, and testing of the applications in support of business solutions.&nbsp;</li><li>&nbsp;Strong ability to analyze functional needs and drive the design for positive business outcomes; recommends solutions that are aligned with business / IT strategies and complies with corporate architectural standards</li><li>&nbsp;Join Business application development, lead/coach technical project team and review development solution/code&nbsp;</li><li>&nbsp;Play architecture role in re-designing critical services &amp; performance improvements</li><li>&nbsp;Code re-engineering &amp; legacy application enhancement • Maintenance and support business applications&nbsp;</li><li>&nbsp;Review code &amp; control code quality.</li><li>&nbsp;Main skill: Java / AWS/Azure&nbsp;</li></ul><h2><strong>Yêu cầu công việc</strong></h2><ul><li>Bachelor’s and/or master’s degree in computer science or related field</li><li>Experience (5+ years) with Java&nbsp;</li><li>Proficient in web programming with extensive knowledge of Java, Spring framework, Hibernate, SQL</li><li>Proven track record working on high volume, high profile websites</li><li>Expert at cross-browser compatibility, responsive mobile design, organized code structure, and highly interactive user experiences&nbsp;</li><li>Proficient in web programming with extensive knowledge of HTML/CSS/JavaScript&nbsp;</li><li>Strong understanding of internet, systems, web technologies and web service architectures</li><li>In-depth knowledge and experience of the software development lifecycle</li><li>Understanding of W3C web standards and accessibility guidelines is required</li><li>A good understanding of configuration management, testing and bug tracking</li><li>Strong communication skills. Able to easily collaborate with peers and customers</li><li>Experience with Microsoft Visual Studio development tools a strong plus</li><li>Experience delivering software in an Agile or Lean delivery method</li><li>Excellent communication and negotiation skills, helping lead and facilitate major and minor&nbsp;</li><li>Experience with SOAP/REST web service integration/implementation experience</li><li>Strong SQL query knowledge&nbsp;</li><li>Experience in production support design decisions with our business partners and the rest of the development team members</li><li>Excellent problem-solving skills with strong attention to detail&nbsp;</li><li>Ability to successfully resolve stressful production issues&nbsp;</li><li>Azure/AWS cloud development experience</li></ul><h2><strong>Tại sao bạn sẽ yêu thích làm việc tại đây</strong></h2><p>Join FPT Software – You can make it too!</p><p>You can catch up with unlimited opportunities to work and live in different countries over the world, join world class software projects with trendiest technologies, innovative products &amp; services that bring great values to millions of people around the world, such as the world’s largest airplane brand, biggest broadcast satellite services in the US, the leading manufacturer of postage meter and mailroom equipment in EU, etc.</p><p>You can choose your career path to become a technology expert or a professional manager which best fits your desire, qualifications and characteristics in an equal opportunity and open-minded culture workplace.</p><p>You can balance your working and spiritual life in the environment of youth, multinational culture and creativity, improve impressively vital soft skills including English, Japanese, French… and communication skills through daily direct communication with global customers and employees.</p><p>FPT Software is proud to accompany with thousands of individuals to continuously develop and explore their career path.</p><p>You can make it too!</p>', N'Experience (5+ years) with Java ', 2, N'No', 18000000, 1, N'Technical Lead ', N'Full time', 0, 1, N'ba09b658-0164-416c-9cb6-c6383ca0512a')
INSERT [dbo].[recruitment] ([id_recruitment], [address], [create_at], [deadline], [description], [experience], [quantity], [rank], [salary], [status], [title], [type], [views], [id_category], [company_id]) VALUES (N'9c398e1d-b088-4afe-91b3-effe2b36e06a', N' FPT Cau Giay Building, Cau Giay, Ha Noi', N'2023-08-08', N'2023-09-09', N'<h2><strong>3 Lý do để gia nhập công ty</strong></h2><ul><li>Global Exposure</li><li>Fast Track Career</li><li>Diversified Jobs &amp; Technologies</li></ul><h2><strong>Mô tả công việc</strong></h2><ul><li>Our vendor is the world leading company in IoT Wireless technology including Bluetooth, Zigbee, Z-wave, Proprietary wireless, Thread, Wi-Fi, Wi-Sun. The projects are dealing with hardware integration and software platform for IOT applications such as Home Automation &amp; Security, lighting, Industrial Automation, Smart Metering, Retail &amp; Commercial. The software platforms include RTOS, drivers, security and middleware while hardware platforms include IoT SOC, radio modules and development boards. With the future of a smarter world and stage-of-art radio frequency, mesh networking, high-end tool chains from the vendor, you have a chance to look for deeper software development to solve key problems in IoT nowadays.</li><li>You will participate in the design, development, testing and documentation for embedded software part of IoT applications, providing consultant technical support for end user. Responsible for software delivery, communicate with leader, project manager or customer.</li></ul><h2><strong>Yêu cầu công việc</strong></h2><p><strong>Basic Qualification:</strong></p><ul><li>Strong in Embedded C programming</li><li>From 1.5 years experience in embedded software development</li><li>Basic English verbal communication</li></ul><p><strong>Preferred Qualification:</strong></p><ul><li>Familiar with embedded protocols such as UART, SPI, I2C, I2S</li><li>Embedded operating systems (RTOS and Linux Embedded)</li><li>Experience working with IoT project is plus, included wireless stacks (Zigbee, Wi-Fi, Thread, Bluetooth and Proprietary)</li></ul><h2><strong>Tại sao bạn sẽ yêu thích làm việc tại đây</strong></h2><ul><li>“FPT care” health insurance provided by AON and is exclusive for FPT employees.</li><li>Annual Summer Vacation: follows company’s policy and starts from May every year</li><li>Salary review 2 times/year or on excellent performance</li><li>International, dynamic, friendly working environment</li><li>Annual leave, working conditions follow Vietnam labor laws.</li><li>Other allowances: lunch allowance, working on-site allowance, etc.<strong>3 Lý do để gia nhập công ty</strong></li><li>Global Exposure</li><li>Fast Track Career</li><li>Diversified Jobs &amp; Technologies</li></ul><h2><strong>Mô tả công việc</strong></h2><ul><li>Our vendor is the world leading company in IoT Wireless technology including Bluetooth, Zigbee, Z-wave, Proprietary wireless, Thread, Wi-Fi, Wi-Sun. The projects are dealing with hardware integration and software platform for IOT applications such as Home Automation &amp; Security, lighting, Industrial Automation, Smart Metering, Retail &amp; Commercial. The software platforms include RTOS, drivers, security and middleware while hardware platforms include IoT SOC, radio modules and development boards. With the future of a smarter world and stage-of-art radio frequency, mesh networking, high-end tool chains from the vendor, you have a chance to look for deeper software development to solve key problems in IoT nowadays.</li><li>You will participate in the design, development, testing and documentation for embedded software part of IoT applications, providing consultant technical support for end user. Responsible for software delivery, communicate with leader, project manager or customer.</li></ul><h2><strong>Yêu cầu công việc</strong></h2><p><strong>Basic Qualification:</strong></p><ul><li>Strong in Embedded C programming</li><li>From 1.5 years experience in embedded software development</li><li>Basic English verbal communication</li></ul><p><strong>Preferred Qualification:</strong></p><ul><li>Familiar with embedded protocols such as UART, SPI, I2C, I2S</li><li>Embedded operating systems (RTOS and Linux Embedded)</li><li>Experience working with IoT project is plus, included wireless stacks (Zigbee, Wi-Fi, Thread, Bluetooth and Proprietary)</li></ul><h2><strong>Tại sao bạn sẽ yêu thích làm việc tại đây</strong></h2><ul><li>“FPT care” health insurance provided by AON and is exclusive for FPT employees.</li><li>Annual Summer Vacation: follows company’s policy and starts from May every year</li><li>Salary review 2 times/year or on excellent performance</li><li>International, dynamic, friendly working environment</li><li>Annual leave, working conditions follow Vietnam labor laws.</li><li>Other allowances: lunch allowance, working on-site allowance, etc.</li></ul>', N'From 1.5 years experience in embedded software developmentFrom 1.5 years experience in embedded software development', 2, N'Senior ', 50000000, 1, N'Embedded Software Engineer (IoT Project) - Up to $2800', N'Full time', 0, 7, N'ba09b658-0164-416c-9cb6-c6383ca0512a')
GO
SET IDENTITY_INSERT [dbo].[role] ON 

INSERT [dbo].[role] ([id_role], [role_name]) VALUES (1, N'ROLE_
CANDIDATEROLE_
CANDIDATE')
INSERT [dbo].[role] ([id_role], [role_name]) VALUES (2, N'ROLE_RECRUITERROLE_RECRUITER')
SET IDENTITY_INSERT [dbo].[role] OFF
GO
INSERT [dbo].[users] ([id_user], [address], [description], [email], [enable], [full_name], [image], [password], [phone_number], [id_company], [role_id]) VALUES (N'4ae7a43b-1bbb-44b3-adc2-36560df2e5fb', N'Nguyễn Thái Sơn', N'', N't@gmail.com', 1, N'Nguyễn Phúc Thịnh', NULL, N'$2a$12$nvjU7b.RbHxjmxhik.lKbOYvn04hWWbrRwKl7JrP4a7TaA15K1I3i', N'0866267672', NULL, 1)
INSERT [dbo].[users] ([id_user], [address], [description], [email], [enable], [full_name], [image], [password], [phone_number], [id_company], [role_id]) VALUES (N'87cec5cb-280d-4155-a658-b25fc4647751', N'Nguyễn Thái Sơn', N'', N'hana14t2@gmail.com', 1, N'Lucy Hana', NULL, N'$2a$12$stRWZ9E792Ns736fUiP2TeY.70lE9458emxtbRNN/5tKwt0MLZC9e', N'0968737247', N'ba09b658-0164-416c-9cb6-c6383ca0512a', 2)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_kw9721csgli3g87n3483av8oo]    Script Date: 8/8/2023 1:55:13 AM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_kw9721csgli3g87n3483av8oo] ON [dbo].[curriculum_vitae]
(
	[user_id] ASC
)
WHERE ([user_id] IS NOT NULL)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[apply_post]  WITH CHECK ADD  CONSTRAINT [FK616uxvqhtddthhvq0na4aluhj] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id_user])
GO
ALTER TABLE [dbo].[apply_post] CHECK CONSTRAINT [FK616uxvqhtddthhvq0na4aluhj]
GO
ALTER TABLE [dbo].[apply_post]  WITH CHECK ADD  CONSTRAINT [FKsobes6hef7fhussdxgvntmalc] FOREIGN KEY([recruitment_id])
REFERENCES [dbo].[recruitment] ([id_recruitment])
GO
ALTER TABLE [dbo].[apply_post] CHECK CONSTRAINT [FKsobes6hef7fhussdxgvntmalc]
GO
ALTER TABLE [dbo].[curriculum_vitae]  WITH CHECK ADD  CONSTRAINT [FKiitouosgqijxl80ibvejs6ol5] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id_user])
GO
ALTER TABLE [dbo].[curriculum_vitae] CHECK CONSTRAINT [FKiitouosgqijxl80ibvejs6ol5]
GO
ALTER TABLE [dbo].[follow_company]  WITH CHECK ADD  CONSTRAINT [FK8mku7scn4p0jxwcge3nfdnl22] FOREIGN KEY([company_id])
REFERENCES [dbo].[companies] ([id_company])
GO
ALTER TABLE [dbo].[follow_company] CHECK CONSTRAINT [FK8mku7scn4p0jxwcge3nfdnl22]
GO
ALTER TABLE [dbo].[follow_company]  WITH CHECK ADD  CONSTRAINT [FKrtesisueur1twedmb0jb5u8oy] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id_user])
GO
ALTER TABLE [dbo].[follow_company] CHECK CONSTRAINT [FKrtesisueur1twedmb0jb5u8oy]
GO
ALTER TABLE [dbo].[recruitment]  WITH CHECK ADD  CONSTRAINT [FK153ucx1yf6jimmiqqyuenc3d0] FOREIGN KEY([id_category])
REFERENCES [dbo].[category] ([id_category])
GO
ALTER TABLE [dbo].[recruitment] CHECK CONSTRAINT [FK153ucx1yf6jimmiqqyuenc3d0]
GO
ALTER TABLE [dbo].[recruitment]  WITH CHECK ADD  CONSTRAINT [FK90ty14ilinyca4610n6evhf3h] FOREIGN KEY([company_id])
REFERENCES [dbo].[companies] ([id_company])
GO
ALTER TABLE [dbo].[recruitment] CHECK CONSTRAINT [FK90ty14ilinyca4610n6evhf3h]
GO
ALTER TABLE [dbo].[save_job]  WITH CHECK ADD  CONSTRAINT [FK7mhpldb69f4sjyn9ijta42tep] FOREIGN KEY([recruitment_id])
REFERENCES [dbo].[recruitment] ([id_recruitment])
GO
ALTER TABLE [dbo].[save_job] CHECK CONSTRAINT [FK7mhpldb69f4sjyn9ijta42tep]
GO
ALTER TABLE [dbo].[save_job]  WITH CHECK ADD  CONSTRAINT [FKmjkhs5o2q0mlfmegffe5stfg0] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id_user])
GO
ALTER TABLE [dbo].[save_job] CHECK CONSTRAINT [FKmjkhs5o2q0mlfmegffe5stfg0]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FK4qu1gr772nnf6ve5af002rwya] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id_role])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FK4qu1gr772nnf6ve5af002rwya]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FKquvytbr669tfa2bcednw3iy3s] FOREIGN KEY([id_company])
REFERENCES [dbo].[companies] ([id_company])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FKquvytbr669tfa2bcednw3iy3s]
GO
USE [master]
GO
ALTER DATABASE [JobCV] SET  READ_WRITE 
GO
