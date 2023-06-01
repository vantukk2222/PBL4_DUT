using Microsoft.EntityFrameworkCore;
using WebAPI.Model;

namespace WebAPI.Data
{
    public class MyDbContext : DbContext
    {
        public MyDbContext(DbContextOptions<MyDbContext> options) :
            base(options)
        { }
        public DbSet<Account>? Accounts { get; set; }
        public DbSet<Person>? People { get; set; }
        public DbSet<Sach>? Saches { get; set; }
        public DbSet<HoaDon>? HoaDons { get; set; }
        public DbSet<ChiTietHoaDon>? ChiTietHoaDons { get; set; }
        public DbSet<Cart>? Carts { get; set; }
    }
}
